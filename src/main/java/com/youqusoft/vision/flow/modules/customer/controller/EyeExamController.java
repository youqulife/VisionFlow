package com.youqusoft.vision.flow.modules.customer.controller;

import com.youqusoft.vision.flow.modules.customer.service.EyeExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;
import com.youqusoft.vision.flow.modules.customer.model.query.EyeExamQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.EyeExamVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * 验光记录前端控制层
 *
 * @author youqusoft
 * @since 2025-10-01 11:31
 */
@Tag(name = "验光记录接口")
@RestController
@RequestMapping("/api/v1/eye-exam")
@RequiredArgsConstructor
public class EyeExamController  {

    private final EyeExamService eyeExamService;

    @Operation(summary = "验光记录分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('customer:eye-exam:query')")
    public PageResult<EyeExamVO> getEyeExamPage(EyeExamQuery queryParams ) {
        IPage<EyeExamVO> result = eyeExamService.getEyeExamPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增验光记录")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('customer:eye-exam:add')")
    public Result<Void> saveEyeExam(@RequestBody @Valid EyeExamForm formData ) {
        boolean result = eyeExamService.saveEyeExam(formData);
        return Result.judge(result);
    }

    @Operation(summary = "获取验光记录表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('customer:eye-exam:edit')")
    public Result<EyeExamForm> getEyeExamForm(
        @Parameter(description = "验光记录ID") @PathVariable Long id
    ) {
        EyeExamForm formData = eyeExamService.getEyeExamFormData(id);
        return Result.success(formData);
    }

    @Operation(summary = "修改验光记录")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('customer:eye-exam:edit')")
    public Result<Void> updateEyeExam(
            @Parameter(description = "验光记录ID") @PathVariable Long id,
            @RequestBody @Validated EyeExamForm formData
    ) {
        boolean result = eyeExamService.updateEyeExam(id, formData);
        return Result.judge(result);
    }

    @Operation(summary = "删除验光记录")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('customer:eye-exam:delete')")
    public Result<Void> deleteEyeExams(
        @Parameter(description = "验光记录ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = eyeExamService.deleteEyeExams(ids);
        return Result.judge(result);
    }
}
