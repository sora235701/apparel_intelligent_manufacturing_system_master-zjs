package org.jeecg.modules.demo.fzlx.controller;

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
import org.jeecg.modules.demo.fzlx.entity.ClothTypeManager;
import org.jeecg.modules.demo.fzlx.service.IClothTypeManagerService;

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
 * @Description: cloth_type_manager
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Api(tags="cloth_type_manager")
@RestController
@RequestMapping("/fzlx/clothTypeManager")
@Slf4j
public class ClothTypeManagerController extends JeecgController<ClothTypeManager, IClothTypeManagerService> {
	@Autowired
	private IClothTypeManagerService clothTypeManagerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param clothTypeManager
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "cloth_type_manager-分页列表查询")
	@ApiOperation(value="cloth_type_manager-分页列表查询", notes="cloth_type_manager-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ClothTypeManager>> queryPageList(ClothTypeManager clothTypeManager,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ClothTypeManager> queryWrapper = QueryGenerator.initQueryWrapper(clothTypeManager, req.getParameterMap());
		Page<ClothTypeManager> page = new Page<ClothTypeManager>(pageNo, pageSize);
		IPage<ClothTypeManager> pageList = clothTypeManagerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param clothTypeManager
	 * @return
	 */
	@AutoLog(value = "cloth_type_manager-添加")
	@ApiOperation(value="cloth_type_manager-添加", notes="cloth_type_manager-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:cloth_type_manager:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ClothTypeManager clothTypeManager) {
		clothTypeManagerService.save(clothTypeManager);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param clothTypeManager
	 * @return
	 */
	@AutoLog(value = "cloth_type_manager-编辑")
	@ApiOperation(value="cloth_type_manager-编辑", notes="cloth_type_manager-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:cloth_type_manager:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ClothTypeManager clothTypeManager) {
		clothTypeManagerService.updateById(clothTypeManager);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "cloth_type_manager-通过id删除")
	@ApiOperation(value="cloth_type_manager-通过id删除", notes="cloth_type_manager-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:cloth_type_manager:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		clothTypeManagerService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "cloth_type_manager-批量删除")
	@ApiOperation(value="cloth_type_manager-批量删除", notes="cloth_type_manager-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:cloth_type_manager:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.clothTypeManagerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "cloth_type_manager-通过id查询")
	@ApiOperation(value="cloth_type_manager-通过id查询", notes="cloth_type_manager-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ClothTypeManager> queryById(@RequestParam(name="id",required=true) String id) {
		ClothTypeManager clothTypeManager = clothTypeManagerService.getById(id);
		if(clothTypeManager==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(clothTypeManager);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param clothTypeManager
    */
    //@RequiresPermissions("org.jeecg.modules.demo:cloth_type_manager:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ClothTypeManager clothTypeManager) {
        return super.exportXls(request, clothTypeManager, ClothTypeManager.class, "cloth_type_manager");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("cloth_type_manager:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ClothTypeManager.class);
    }

}
