package com.kedacom.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
/**
 * Created by kedacom on 2019/8/2.
 */
@Entity
@Table(name="tab_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private BigDecimal money;
    private String avatarUrl;
    private Integer status;

}
