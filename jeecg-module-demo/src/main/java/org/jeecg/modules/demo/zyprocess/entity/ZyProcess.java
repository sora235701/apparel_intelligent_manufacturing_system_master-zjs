package org.jeecg.modules.demo.zyprocess.entity;

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
 * @Description: 基本工序表
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Data
@TableName("zy_process")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_process对象", description="基本工序表")
public class ZyProcess implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**内部编号*/
	@Excel(name = "内部编号", width = 15)
    @ApiModelProperty(value = "内部编号")
    private String createId;
	/**工序代码*/
	@Excel(name = "工序代码", width = 15)
    @ApiModelProperty(value = "工序代码")
    private String processCode;
	/**工序名称*/
	@Excel(name = "工序名称", width = 15)
    @ApiModelProperty(value = "工序名称")
    private String processName;
	/**工序描述*/
	@Excel(name = "工序描述", width = 15)
    @ApiModelProperty(value = "工序描述")
    private String processDescribe;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private String image;
	/**视频*/
	@Excel(name = "视频", width = 15)
    @ApiModelProperty(value = "视频")
    private String vedio;
	/**做工说明*/
	@Excel(name = "做工说明", width = 15)
    @ApiModelProperty(value = "做工说明")
    private String mkExplain;
	/**品质要求*/
	@Excel(name = "品质要求", width = 15)
    @ApiModelProperty(value = "品质要求")
    private String qualityRequire;
	/**工序时间*/
	@Excel(name = "工序时间", width = 15)
    @ApiModelProperty(value = "工序时间")
    private Integer processTime;
	/**工序单价*/
	@Excel(name = "工序单价", width = 15)
    @ApiModelProperty(value = "工序单价")
    private Double price;
	/**工序等级*/
	@Excel(name = "工序等级", width = 15)
    @ApiModelProperty(value = "工序等级")
    private Integer grade;
	/**针距*/
	@Excel(name = "针距", width = 15)
    @ApiModelProperty(value = "针距")
    private Integer needlePitch;
	/**手工宽放*/
	@Excel(name = "手工宽放", width = 15)
    @ApiModelProperty(value = "手工宽放")
    private Double manualWide;
	/**手工时间*/
	@Excel(name = "手工时间", width = 15)
    @ApiModelProperty(value = "手工时间")
    private Integer manualTime;
	/**设备转速*/
	@Excel(name = "设备转速", width = 15)
    @ApiModelProperty(value = "设备转速")
    private Integer machineSpeed;
	/**设备宽放*/
	@Excel(name = "设备宽放", width = 15)
    @ApiModelProperty(value = "设备宽放")
    private Double machineWide;
	/**设备时间*/
	@Excel(name = "设备时间", width = 15)
    @ApiModelProperty(value = "设备时间")
    private Integer machineTime;
	/**设备浮于*/
	@Excel(name = "设备浮于", width = 15)
    @ApiModelProperty(value = "设备浮于")
    private Double machineFloat;
	/**绑包宽放*/
	@Excel(name = "绑包宽放", width = 15)
    @ApiModelProperty(value = "绑包宽放")
    private Double bundleWide;
	/**绑包时间*/
	@Excel(name = "绑包时间", width = 15)
    @ApiModelProperty(value = "绑包时间")
    private Integer bundleTime;
	/**合计（手工TMU）*/
	@Excel(name = "合计（手工TMU）", width = 15)
    @ApiModelProperty(value = "合计（手工TMU）")
    private Integer totalManualTmu;
	/**合计（设备TMU）*/
	@Excel(name = "合计（设备TMU）", width = 15)
    @ApiModelProperty(value = "合计（设备TMU）")
    private Integer totalMachineTmu;
	/**合计（手工秒）*/
	@Excel(name = "合计（手工秒）", width = 15)
    @ApiModelProperty(value = "合计（手工秒）")
    private Integer totalMaunal;
	/**合计（设备秒）*/
	@Excel(name = "合计（设备秒）", width = 15)
    @ApiModelProperty(value = "合计（设备秒）")
    private Integer totalMachine;
	/**企业id*/
	@Excel(name = "企业id", width = 15)
    @ApiModelProperty(value = "企业id")
    private String enterpriseId;
	/**服装类型id*/
	@Excel(name = "服装类型id", width = 15)
    @ApiModelProperty(value = "服装类型id")
    private String clothsTypeId;
	/**工段代码*/
	@Excel(name = "工段代码", width = 15)
    @ApiModelProperty(value = "工段代码")
    private Integer worksectionCode;
	/**工序类型*/
	@Excel(name = "工序类型", width = 15)
    @ApiModelProperty(value = "工序类型")
    private Integer processType;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
