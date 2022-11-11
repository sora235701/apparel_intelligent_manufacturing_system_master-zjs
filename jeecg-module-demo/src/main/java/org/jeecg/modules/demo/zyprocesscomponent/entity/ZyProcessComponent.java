package org.jeecg.modules.demo.zyprocesscomponent.entity;

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
 * @Description: 部件工序表
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Data
@TableName("zy_process_component")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zy_process_component对象", description="部件工序表")
public class ZyProcessComponent implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**内部编号*/
	@Excel(name = "内部编号", width = 15)
    @ApiModelProperty(value = "内部编号")
    private String createId;
	/**工序id*/
	@Excel(name = "工序id", width = 15)
    @ApiModelProperty(value = "工序id")
    private String processId;
	/**部件id*/
	@Excel(name = "部件id", width = 15)
    @ApiModelProperty(value = "部件id")
    private String componentId;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
}
