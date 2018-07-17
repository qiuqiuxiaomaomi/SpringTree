package com.bonaparte.entity;

import javax.persistence.*;

@Table(name = "p_groovy")
public class Groovy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "groovy_script")
    private String groovyScript;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return groovy_script
     */
    public String getGroovyScript() {
        return groovyScript;
    }

    /**
     * @param groovyScript
     */
    public void setGroovyScript(String groovyScript) {
        this.groovyScript = groovyScript;
    }
}