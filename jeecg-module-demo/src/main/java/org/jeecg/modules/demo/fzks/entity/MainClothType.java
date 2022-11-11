package org.jeecg.modules.demo.fzks.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 服装款式
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Data
@TableName("main_cloth_type")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="main_cloth_type对象", description="服装款式")
public class MainClothType implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**服装类型*/
	@Excel(name = "服装类型", width = 15)
    @ApiModelProperty(value = "服装类型")
    private String clothType;
	/**形式*/
	@Excel(name = "形式", width = 15)
    @ApiModelProperty(value = "形式")
    private String clothForm;
	/**编号*/
	@Excel(name = "编号", width = 15)
    @ApiModelProperty(value = "编号")
    private String clothId;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**企业名称*/
	@Excel(name = "企业名称", width = 15)
    @ApiModelProperty(value = "企业名称")
    private String companyName;
	/**规格*/
	@Excel(name = "规格", width = 15)
    @ApiModelProperty(value = "规格")
    private String specs;
	/**销售地区*/
	@Excel(name = "销售地区", width = 15)
    @ApiModelProperty(value = "销售地区")
    private String salesArea;
	/**产品概述*/
	@Excel(name = "产品概述", width = 15)
    @ApiModelProperty(value = "产品概述")
    private String prodIntro;
	/**结构*/
	@Excel(name = "结构", width = 15)
    @ApiModelProperty(value = "结构")
    private String clothStructure;
	/**特征*/
	@Excel(name = "特征", width = 15)
    @ApiModelProperty(value = "特征")
    private String clothFeature;
	/**测量方法*/
	@Excel(name = "测量方法", width = 15)
    @ApiModelProperty(value = "测量方法")
    private String measureMethod;
	/**误差*/
	@Excel(name = "误差", width = 15)
    @ApiModelProperty(value = "误差")
    private String wucha;
	/**定额用料*/
	@Excel(name = "定额用料", width = 15)
    @ApiModelProperty(value = "定额用料")
    private String quotaMaterials;
	/**折叠搭配与包装*/
	@Excel(name = "折叠搭配与包装", width = 15)
    @ApiModelProperty(value = "折叠搭配与包装")
    private String foldingAndPackaging;
	/**配件与标志*/
	@Excel(name = "配件与标志", width = 15)
    @ApiModelProperty(value = "配件与标志")
    private String accessoriesAndLogos;
	/**缝纫形式*/
	@Excel(name = "缝纫形式", width = 15)
    @ApiModelProperty(value = "缝纫形式")
    private String sewingType;
}
