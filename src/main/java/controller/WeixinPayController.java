package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import constant.ControllerConstants;
import constant.WeixinPayProps;
import dao.mapper.ChargeMapper;
import entity.Charge;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ChargeService;
import util.ControllerUtil;
import util.StringTools;
import util.WechatUtils;
import util.XmltoJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yangmingquan on 2018/1/23.
 * 微信支付
 * 简介: 公众号接入微信支付，
 *          --前端传参数发给后台
 *          --后台构造参数调用微信统一下单接口，并传递回调URL
 *          --后台接收微信统一下单接口返回数据给前端
 *          --前端调用微信的JS SDK打开微信支付付款
 *          --微信接收支付完成后回调后台回调URL,后台处理自身逻辑
 */
@RestController("/WeixinPay")
@RequestMapping("/charge")
public class WeixinPayController{
    public static final Log logger = LogFactory.getLog(WeixinPayController.class);
    @Autowired
    public ChargeService chargeService;
    @Autowired
    public ChargeMapper partyChargeMapper;
    @Autowired
    public WeixinPayProps weixinPayProps;

    @ApiOperation(value = "微信支付接口",notes = "微信支付接口",httpMethod = "POST")
    @ApiImplicitParam(name = "bean", value = "微信支付接口", required = true, dataType = "PartyCharge")
    @ApiResponse(code = 200,message = "微信支付", response = Map.class)
    @RequestMapping(value = "/weixinPay")
    public Object weixinPay(@RequestBody Charge bean) throws Exception{
        String nonceStr = StringTools.randonStr();//暂时不变
        // 加密，这里只列举必填字段
        Charge charge = partyChargeMapper.selectByPrimaryKey(bean.getId());

        //统一订单请求
        SortedMap<String, Object> map = new TreeMap<String, Object>();
        //商品描述
        map.put("body", charge.getChargeTime() +"商品费用信息为"+ charge.getMoney());
        //商户平台id
        map.put("mch_id", weixinPayProps.getMchId());
        //公众号id
        map.put("appid", weixinPayProps.getAppId());
        //随机字符串
        map.put("nonce_str", nonceStr);
        //异步回调api
        map.put("notify_url", weixinPayProps.getNotifyUrl());
        //支付ip
        map.put("spbill_create_ip", chargeService.getIp());
        //商品订单号
        map.put("out_trade_no", charge.getId()+"");
        //真实金额
        map.put("total_fee",  (int)(charge.getMoney()*100));
        map.put("openid", bean.getOpenId());
        //JSAPI、h5调用
        map.put("trade_type", "JSAPI");
        map.put("sign", WechatUtils.createSign("UTF-8", map,weixinPayProps.getKey()));

        String xml = "<xml>" +
                "<appid><![CDATA["+ map.get("appid") +"]]></appid>"+
                "<body><![CDATA["+ map.get("body") +"]]></body>"+
                "<mch_id><![CDATA["+ map.get("mch_id") +"]]></mch_id>"+
                "<nonce_str><![CDATA["+ nonceStr +"]]></nonce_str>"+
                "<notify_url><![CDATA["+ map.get("notify_url") +"]]></notify_url>"+
                "<out_trade_no><![CDATA["+ map.get("out_trade_no") +"]]></out_trade_no>"+
                "<spbill_create_ip><![CDATA["+ map.get("spbill_create_ip") +"]]></spbill_create_ip>"+
                "<total_fee><![CDATA["+ (int)(charge.getMoney()*100) + "" +"]]></total_fee>"+
                "<trade_type><![CDATA[JSAPI]]></trade_type>"+
                "<openid><![CDATA["+ map.get("openid")+"]]></openid>"+
                "<sign>"+ map.get("sign") +"</sign>"+
                "</xml>";
        System.out.println(xml);
        // 请求
        String response = "";
        try {
            response = WechatUtils.httpsRequest(weixinPayProps.getWxUnifiedorder(),"POST", xml);
        } catch (Exception e) {
            return null;
        }
        logger.info("请求/pay/unifiedorder下单接口后返回数据：" + response);
        //处理请求结果
        Map<String, String> mapResult = null;
        try {
            mapResult = WechatUtils.doXMLParse(response);
            logger.info("微信支付统一下单请求成功，获得的Prepay_id是：");
        } catch (Exception e){
            logger.error("微信支付统一下单请求错误：");
            return null;
        }
        //拼接jssdk需要的参数
        SortedMap<String, Object> payMap = new TreeMap<String, Object>();
        String prepay_id = mapResult.get("prepay_id");
        String timeStamp = getSecondTimestampTwo();
        String packageStr = "prepay_id="+prepay_id;
        String signType = "MD5";
        payMap.put("appId",weixinPayProps.getAppId());
        payMap.put("timeStamp",timeStamp);
        payMap.put("nonceStr",StringTools.randonStr());
        payMap.put("package",packageStr);
        payMap.put("signType",signType);
        payMap.put("paySign", WechatUtils.createSign("UTF-8", payMap,weixinPayProps.getKey()));
        System.out.println(JSON.toJSONString(payMap));
        return  payMap;
    }

    public String getSecondTimestampTwo(){
        Date date = new Date();
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp).toString();
    }

    @ApiOperation(value = "微信支付结束回调接口",notes = "微信支付结束回调接口",httpMethod = "POST")
    @ApiResponse(code = 200,message = "微信支付结束回调接口", response = Map.class)
    @RequestMapping(value = "/weixinPayCallback", method = {RequestMethod.POST, RequestMethod.GET})
    public void weixinPayCallback(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, Object> resultMap = ControllerUtil.defaultSuccResult();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            InputStream in = request.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            String content = new String(out.toByteArray(), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(XmltoJsonUtil.xml2JSON(content));
            System.out.println(JSON.toJSONString(jsonObject));
            JSONObject result_xml = jsonObject.getJSONObject("xml");
            JSONArray result_code =  result_xml.getJSONArray("result_code");
            String code = (String)result_code.get(0);
            if(code.equalsIgnoreCase("FAIL")){
                resultMap = ControllerUtil.defaultErrResult();
                resultMap.put(ControllerConstants.MSG_KEY, "微信统一订单下单失败");
                response.getWriter().write(WechatUtils.setXml("SUCCESS", "OK"));
            }else if(code.equalsIgnoreCase("SUCCESS")){
                resultMap = ControllerUtil.defaultErrResult();
                resultMap.put(ControllerConstants.MSG_KEY, "微信统一订单下单成功");
                JSONArray out_trade_no = result_xml.getJSONArray("out_trade_no");//订单编号
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("orderNum", (String)out_trade_no.get(0));
                map.put("consumState", 1);
                //更改状态
                Integer orderNum = Integer.valueOf((String)map.get("orderNum"));
                Charge partyCharge = partyChargeMapper.selectByPrimaryKey(orderNum);
                if (partyCharge != null){
                    //将支付状态改为已支付
                    partyCharge.setStatus(2);
                    //微信支付类型为2
                    partyCharge.setType(2);
                    partyCharge.setUtime(new Date());
                    partyChargeMapper.updateByPrimaryKey(partyCharge);
                }
                response.getWriter().write(WechatUtils.setXml("SUCCESS", "OK"));
            }
        } catch (Exception e){
            logger.error(e);
        }
    }
}
