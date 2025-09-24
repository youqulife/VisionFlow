package com.youqusoft.vision.flow.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 绑定邮箱表单
 *
 * @author Ray.Hao
 * @since 2024/8/19
 */
@Schema(description = "绑定邮箱表单")
@Data
public class EmailBindingForm {

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "验证码")
    private String code;

}
