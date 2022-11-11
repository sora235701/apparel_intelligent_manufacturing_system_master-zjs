package org.jeecg.modules.demo.zystylemodularcopy.controller;

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
import org.jeecg.modules.demo.zystylemodularcopy.entity.ZyStyleModularCopy;
import org.jeecg.modules.demo.zystylemodularcopy.service.IZyStyleModularCopyService;

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
 * @Description: 款式工序表
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Api(tags="款式工序表")
@RestController
@RequestMapping("/zystylemodularcopy/zyStyleModularCopy")
@Slf4j
public class ZyStyleModularCopyController extends JeecgController<ZyStyleModularCopy, IZyStyleModularCopyService> {
	@Autowired
	private IZyStyleModularCopyService zyStyleModularCopyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zyStyleModularCopy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "款式工序表-分页列表查询")
	@ApiOperation(value="款式工序表-分页列表查询", notes="款式工序表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ZyStyleModularCopy>> queryPageList(ZyStyleModularCopy zyStyleModularCopy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZyStyleModularCopy> queryWrapper = QueryGenerator.initQueryWrapper(zyStyleModularCopy, req.getParameterMap());
		Page<ZyStyleModularCopy> page = new Page<ZyStyleModularCopy>(pageNo, pageSize);
		IPage<ZyStyleModularCopy> pageList = zyStyleModularCopyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param zyStyleModularCopy
	 * @return
	 */
	@AutoLog(value = "款式工序表-添加")
	@ApiOperation(value="款式工序表-添加", notes="款式工序表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_style__modular_copy:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ZyStyleModularCopy zyStyleModularCopy) {
		zyStyleModularCopyService.save(zyStyleModularCopy);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param zyStyleModularCopy
	 * @return
	 */
	@AutoLog(value = "款式工序表-编辑")
	@ApiOperation(value="款式工序表-编辑", notes="款式工序表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_style__modular_copy:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ZyStyleModularCopy zyStyleModularCopy) {
		zyStyleModularCopyService.updateById(zyStyleModularCopy);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "款式工序表-通过id删除")
	@ApiOperation(value="款式工序表-通过id删除", notes="款式工序表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_style__modular_copy:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		zyStyleModularCopyService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "款式工序表-批量删除")
	@ApiOperation(value="款式工序表-批量删除", notes="款式工序表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_style__modular_copy:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyStyleModularCopyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "款式工序表-通过id查询")
	@ApiOperation(value="款式工序表-通过id查询", notes="款式工序表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ZyStyleModularCopy> queryById(@RequestParam(name="id",required=true) String id) {
		ZyStyleModularCopy zyStyleModularCopy = zyStyleModularCopyService.getById(id);
		if(zyStyleModularCopy==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyStyleModularCopy);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyStyleModularCopy
    */
    //@RequiresPermissions("org.jeecg.modules.demo:zy_style__modular_copy:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyStyleModularCopy zyStyleModularCopy) {
        return super.exportXls(request, zyStyleModularCopy, ZyStyleModularCopy.class, "款式工序表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("zy_style__modular_copy:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZyStyleModularCopy.class);
    }

}
