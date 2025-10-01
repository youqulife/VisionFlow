package com.youqusoft.vision.flow.modules.customer.model.form;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 佩戴历史数据对象
 * 存储客户的完整佩戴历史信息
 *
 * @author youqusoft
 * @since 2025-10-01
 */
@Data
public class HistoryData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 基本信息
     */
    private BasicInfo basicInfo;

    /**
     * 当前眼镜信息
     */
    private CurrentGlasses currentGlasses;

    /**
     * 使用模式
     */
    private UsagePatterns usagePatterns;

    /**
     * 舒适度评估
     */
    private ComfortAssessment comfortAssessment;

    /**
     * 特殊需求
     */
    private SpecialNeeds specialNeeds;

    /**
     * 基本信息
     */
    @Data
    public static class BasicInfo implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 是否首次配镜
         */
        private Boolean isFirstGlasses;

        /**
         * 是否曾佩戴过眼镜
         */
        private Boolean hasWornGlasses;

        /**
         * 佩戴状态：current-当前佩戴, previous-曾佩戴但目前未佩戴
         */
        private String wearingStatus;

        /**
         * 佩戴年限
         */
        private Integer wearingYears;

        /**
         * 开始佩戴年龄
         */
        private Integer startAge;

        /**
         * 上次验光时间：within_year-一年内, one_three_years-1-3年, over_three_years-超过3年
         */
        private String lastExamTime;
    }

    /**
     * 当前眼镜信息
     */
    @Data
    public static class CurrentGlasses implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 眼镜类型：progressive-渐进多焦点, single_vision-单光, bifocal-双光,
         *          occupational-职业镜, reading-阅读镜, computer-防疲劳镜
         */
        private String type;

        /**
         * 镜架类型：full_rim-全框, semi_rimless-半框, rimless-无框
         */
        private String frameType;

        /**
         * 镜片材料：resin-树脂, glass-玻璃, pc-聚碳酸酯, ac-丙烯酸树脂, tr-钛树脂
         */
        private String lensMaterial;

        /**
         * 镜片使用月数
         */
        private Integer ageMonths;

        /**
         * 购买地点：our_shop-本店, other_shop-其他眼镜店, online-线上, unknown-不记得
         */
        private String purchaseLocation;

        /**
         * 镜片镀膜：anti_reflection-防反射, blue_light-防蓝光, hard-加硬,
         *          uv-防紫外线, polarized-偏光, none-无镀膜
         */
        private List<String> lensCoating;
    }

    /**
     * 使用模式
     */
    @Data
    public static class UsagePatterns implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 日常佩戴时长：full_day-全天, half_day-半天, occasionally-偶尔
         */
        private String dailyDuration;

        /**
         * 主要使用场景：reading-阅读, computer-电脑工作, driving-驾驶,
         *              office_work-办公, outdoor-户外活动
         */
        private List<String> mainScenarios;

        /**
         * 使用频率：everyday-每天, workday-工作日, occasionally-偶尔, rarely-很少
         */
        private String frequency;
    }

    /**
     * 舒适度评估
     */
    @Data
    public static class ComfortAssessment implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 舒适度等级：very_comfortable-非常舒适, comfortable-舒适,
         *           acceptable-可接受, uncomfortable-不舒适, very_uncomfortable-非常不舒适
         */
        private String comfortLevel;

        /**
         * 视觉满意度：very_satisfied-非常满意, satisfied-满意, neutral-一般,
         *           dissatisfied-不满意, very_dissatisfied-非常不满意
         */
        private String visionSatisfaction;

        /**
         * 具体问题：headache-头痛, dizziness-头晕, eye_strain-眼疲劳,
         *         blurred_vision-视物模糊, double_vision-复视,
         *         frame_discomfort-镜架不适, other-其他
         */
        private List<String> specificIssues;

        /**
         * 问题出现频率：never-从不, rare-很少, occasional-偶尔, frequent-经常, always-总是
         */
        private String issueFrequency;
    }

    /**
     * 特殊需求
     */
    @Data
    public static class SpecialNeeds implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 职业：office_worker-办公室职员, student-学生, driver-司机,
         *      outdoor_worker-户外工作者, precision_worker-精密作业者, retiree-退休, other-其他
         */
        private String occupation;

        /**
         * 爱好：reading-阅读, driving-驾驶, computer-电脑游戏,
         *      sports-运动, music-音乐, outdoor-户外活动
         */
        private List<String> hobbies;

        /**
         * 特殊要求：computer_work-电脑工作, night_driving-夜间驾驶,
         *         sports-运动防护, fashion-时尚外观, lightweight-轻便, none-无特殊要求
         */
        private String specialRequirements;
    }
}