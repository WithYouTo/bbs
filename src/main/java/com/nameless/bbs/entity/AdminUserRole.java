package com.nameless.bbs.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author cindy
 * @since 2019-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdminUserRole对象", description = "")
public class AdminUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer adminUserId;

    private Integer roleId;


}
