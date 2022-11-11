package org.jeecg.modules.demo.ksmk.controller;

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
import org.jeecg.modules.demo.ksmk.entity.TypeModule;
import org.jeecg.modules.demo.ksmk.service.ITypeModuleService;

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
 * @Description: 款式模块管理
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Api(tags="款式模块管理")
@RestController
@RequestMapping("/ksmk/typeModule")
@Slf4j
public class TypeModuleController extends JeecgController<TypeModule, ITypeModuleService> {
	@Autowired
	private ITypeModuleService typeModuleService;
	
	/**
	 * 分页列表查询
	 *
	 * @param typeModule
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "款式模块管理-分页列表查询")
	@ApiOperation(value="款式模块管理-分页列表查询", notes="款式模块管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<TypeModule>> queryPageList(TypeModule typeModule,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TypeModule> queryWrapper = QueryGenerator.initQueryWrapper(typeModule, req.getParameterMap());
		Page<TypeModule> page = new Page<TypeModule>(pageNo, pageSize);
		IPage<TypeModule> pageList = typeModuleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param typeModule
	 * @return
	 */
	@AutoLog(value = "款式模块管理-添加")
	@ApiOperation(value="款式模块管理-添加", notes="款式模块管理-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:type_module:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody TypeModule typeModule) {
		typeModuleService.save(typeModule);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param typeModule
	 * @return
	 */
	@AutoLog(value = "款式模块管理-编辑")
	@ApiOperation(value="款式模块管理-编辑", notes="款式模块管理-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:type_module:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody TypeModule typeModule) {
		typeModuleService.updateById(typeModule);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "款式模块管理-通过id删除")
	@ApiOperation(value="款式模块管理-通过id删除", notes="款式模块管理-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:type_module:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		typeModuleService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "款式模块管理-批量删除")
	@ApiOperation(value="款式模块管理-批量删除", notes="款式模块管理-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:type_module:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.typeModuleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "款式模块管理-通过id查询")
	@ApiOperation(value="款式模块管理-通过id查询", notes="款式模块管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<TypeModule> queryById(@RequestParam(name="id",required=true) String id) {
		TypeModule typeModule = typeModuleService.getById(id);
		if(typeModule==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(typeModule);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param typeModule
    */
    //@RequiresPermissions("org.jeecg.modules.demo:type_module:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TypeModule typeModule) {
        return super.exportXls(request, typeModule, TypeModule.class, "款式模块管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("type_module:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TypeModule.class);
    }

}
