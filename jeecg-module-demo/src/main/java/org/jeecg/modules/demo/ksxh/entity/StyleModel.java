package org.jeecg.modules.demo.ksxh.entity;

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
 * @Description: 款式型号管理
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Data
@TableName("style_model")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="style_model对象", description="款式型号管理")
public class StyleModel implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**款式id*/
	@Excel(name = "款式id", width = 15)
    @ApiModelProperty(value = "款式id")
    private String styleId;
	/**是否默认尺码*/
	@Excel(name = "是否默认尺码", width = 15)
    @ApiModelProperty(value = "是否默认尺码")
    private Integer isdefault;
	/**型号编码*/
	@Excel(name = "型号编码", width = 15)
    @ApiModelProperty(value = "型号编码")
    private Integer modelNumber;
	/**码数*/
	@Excel(name = "码数", width = 15)
    @ApiModelProperty(value = "码数")
    private Integer size;
	/**型*/
	@Excel(name = "型", width = 15)
    @ApiModelProperty(value = "型")
    private String anumbers;
	/**号*/
	@Excel(name = "号", width = 15)
    @ApiModelProperty(value = "号")
    private String bnumbers;
	/**领大*/
	@Excel(name = "领大", width = 15)
    @ApiModelProperty(value = "领大")
    private Double collarLarge;
	/**胸围*/
	@Excel(name = "胸围", width = 15)
    @ApiModelProperty(value = "胸围")
    private Double bust;
	/**袖长*/
	@Excel(name = "袖长", width = 15)
    @ApiModelProperty(value = "袖长")
    private Double sleeveLength;
	/**连肩袖长*/
	@Excel(name = "连肩袖长", width = 15)
    @ApiModelProperty(value = "连肩袖长")
    private Double shslLength;
	/**总肩宽*/
	@Excel(name = "总肩宽", width = 15)
    @ApiModelProperty(value = "总肩宽")
    private Double tsWidth;
	/**袖口*/
	@Excel(name = "袖口", width = 15)
    @ApiModelProperty(value = "袖口")
    private Double cuff;
	/**腰围*/
	@Excel(name = "腰围", width = 15)
    @ApiModelProperty(value = "腰围")
    private Double waistline;
	/**下摆*/
	@Excel(name = "下摆", width = 15)
    @ApiModelProperty(value = "下摆")
    private Double hem;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
