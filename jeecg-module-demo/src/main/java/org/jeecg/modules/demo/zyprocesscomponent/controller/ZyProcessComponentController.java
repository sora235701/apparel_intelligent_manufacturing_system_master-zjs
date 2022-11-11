package org.jeecg.modules.demo.zyprocesscomponent.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.zyprocesscomponent.entity.ZyProcessComponent;
import org.jeecg.modules.demo.zyprocesscomponent.service.IZyProcessComponentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 部件工序表
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Api(tags="部件工序表")
@RestController
@RequestMapping("/zyprocesscomponent/zyProcessComponent")
@Slf4j
public class ZyProcessComponentController extends JeecgController<ZyProcessComponent, IZyProcessComponentService> {
	@Autowired
	private IZyProcessComponentService zyProcessComponentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zyProcessComponent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "部件工序表-分页列表查询")
	@ApiOperation(value="部件工序表-分页列表查询", notes="部件工序表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ZyProcessComponent>> queryPageList(ZyProcessComponent zyProcessComponent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZyProcessComponent> queryWrapper = QueryGenerator.initQueryWrapper(zyProcessComponent, req.getParameterMap());
		Page<ZyProcessComponent> page = new Page<ZyProcessComponent>(pageNo, pageSize);
		IPage<ZyProcessComponent> pageList = zyProcessComponentService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param zyProcessComponent
	 * @return
	 */
	@AutoLog(value = "部件工序表-添加")
	@ApiOperation(value="部件工序表-添加", notes="部件工序表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_component:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ZyProcessComponent zyProcessComponent) {
		zyProcessComponentService.save(zyProcessComponent);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param zyProcessComponent
	 * @return
	 */
	@AutoLog(value = "部件工序表-编辑")
	@ApiOperation(value="部件工序表-编辑", notes="部件工序表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_component:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ZyProcessComponent zyProcessComponent) {
		zyProcessComponentService.updateById(zyProcessComponent);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "部件工序表-通过id删除")
	@ApiOperation(value="部件工序表-通过id删除", notes="部件工序表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_component:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		zyProcessComponentService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "部件工序表-批量删除")
	@ApiOperation(value="部件工序表-批量删除", notes="部件工序表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_component:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyProcessComponentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "部件工序表-通过id查询")
	@ApiOperation(value="部件工序表-通过id查询", notes="部件工序表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ZyProcessComponent> queryById(@RequestParam(name="id",required=true) String id) {
		ZyProcessComponent zyProcessComponent = zyProcessComponentService.getById(id);
		if(zyProcessComponent==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyProcessComponent);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyProcessComponent
    */
    //@RequiresPermissions("org.jeecg.modules.demo:zy_process_component:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyProcessComponent zyProcessComponent) {
        return super.exportXls(request, zyProcessComponent, ZyProcessComponent.class, "部件工序表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("zy_process_component:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZyProcessComponent.class);
    }

}
