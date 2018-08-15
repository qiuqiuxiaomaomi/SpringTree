## 复杂Mysql

<pre>
select * from ((select ppm.name as userName,
pc.name as communityName,
psc.name as serviceCategoryName,
ps.title as title,
psi.start_time as startTime,
psi.id,
psi.parent_id as parentId
from p_service ps
inner join p_service_item psi on psi.parent_id = ps.id and psi.status != 9
inner join p_service_member psm on psm.service_id = ps.id
<if test="uid != null">
    and psm.member_id = #{uid}
</if>
inner join p_community pc on pc.id = ps.community_id
inner join p_service_category psc on psc.id = ps.service_category_id
inner join p_party_member ppm on ppm.id = psm.member_id
where ps.status != 9 and ps.`status` != 0
<if test="serviceId != null">
    and ps.id = #{serviceId}
</if>
<if test="serviceCategoryIds != null">
    and ps.service_category_id in
    <foreach collection="serviceCategoryIds" open="(" item="item" close=")" index="index" separator=",">
        #{item}
    </foreach>
</if>
<if test="communityId != null">
    and ps.community_id = #{communityId}
</if>
<if test="startTime != null">
    and psi.start_time >= #{startTime}
</if>
<if test="endTime != null">
    and psi.end_time &lt;= #{endTime}
</if>
<if test="searchStr != null">
    and ps.title like concat("%", #{searchStr}, "%")
</if>
) as tmq
left JOIN (select target_id,count(1) as remarkCount
from p_comment_service
group by target_id)tm on tmq.id = tm.target_id
LEFT JOIN (select service_id , count(1) as signCount from p_service_record
where record_type = 1
GROUP BY service_id)tm1 on tm1.service_id = tmq.id
LEFT JOIN (select service_id , count(1) as enrollCount from p_service_record
GROUP BY service_id)tm3 on tm3.service_id = tmq.id
left join (select target_id ,AVG(score) as score from p_comment_service
where score is not null
GROUP BY target_id) tm2 on tm2.target_id = tmq.id
)
order by startTime desc
<if test="pageSize != null">
    limit #{startRow},#{pageSize}
</if>
</pre>