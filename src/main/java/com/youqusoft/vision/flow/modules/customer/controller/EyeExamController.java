package com.youqusoft.vision.flow.modules.customer.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youqusoft.vision.flow.common.result.PageResult;
import com.youqusoft.vision.flow.common.result.Result;
import com.youqusoft.vision.flow.modules.customer.model.form.EyeExamForm;
import com.youqusoft.vision.flow.modules.customer.model.query.CustomerQuery;
import com.youqusoft.vision.flow.modules.customer.model.vo.EyeExamVO;
import com.youqusoft.vision.flow.modules.customer.service.EyeExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * 验光记录控制层
 *
 * @author Jack.Zhang
 * @since 2025-09-26
 */
@Tag(name = "验光记录管理")
@RestController
@RequestMapping("/api/v1/eye-exams")
@RequiredArgsConstructor
public class EyeExamController {

    private final EyeExamService eyeExamService;

    @Operation(summary = "验光记录分页列表")
    @GetMapping
    public PageResult<EyeExamVO> getEyeExamPage(
            @Parameter(description = "验光记录查询参数") CustomerQuery queryParams
    ) {
        IPage<EyeExamVO> page = eyeExamService.getEyeExamPage(queryParams);
        return PageResult.success(page);
    }

    @Operation(summary = "保存验光记录")
    @PostMapping
    public Result<Boolean> saveEyeExam(
            @RequestBody @Valid EyeExamForm eyeExamForm
    ) {
        boolean result = eyeExamService.saveEyeExam(eyeExamForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取验光记录表单数据")
    @GetMapping("/{id}")
    public Result<EyeExamForm> getEyeExamForm(
            @Parameter(description = "验光记录ID") @PathVariable Long id
    ) {
        EyeExamForm eyeExamForm = eyeExamService.getEyeExamForm(id);
        return Result.success(eyeExamForm);
    }

    @Operation(summary = "更新验光记录")
    @PutMapping("/{id}")
    public Result<Boolean> updateEyeExam(
            @Parameter(description = "验光记录ID") @PathVariable Long id,
            @RequestBody @Valid EyeExamForm eyeExamForm
    ) {
        boolean result = eyeExamService.updateEyeExam(id, eyeExamForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除验光记录")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteEyeExams(
            @Parameter(description = "验光记录ID数组") @PathVariable Long[] ids
    ) {
        boolean result = eyeExamService.deleteEyeExams(ids);
        return Result.judge(result);
    }
}