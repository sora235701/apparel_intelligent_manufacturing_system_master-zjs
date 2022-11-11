package org.jeecg.modules.demo.zyprocessaction.controller;

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
import org.jeecg.modules.demo.zyprocessaction.entity.ZyProcessAction;
import org.jeecg.modules.demo.zyprocessaction.service.IZyProcessActionService;

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
 * @Description: 工序动作表
 * @Author: jeecg-boot
 * @Date:   2022-11-07
 * @Version: V1.0
 */
@Api(tags="工序动作表")
@RestController
@RequestMapping("/zyprocessaction/zyProcessAction")
@Slf4j
public class ZyProcessActionController extends JeecgController<ZyProcessAction, IZyProcessActionService> {
	@Autowired
	private IZyProcessActionService zyProcessActionService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zyProcessAction
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "工序动作表-分页列表查询")
	@ApiOperation(value="工序动作表-分页列表查询", notes="工序动作表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<ZyProcessAction>> queryPageList(ZyProcessAction zyProcessAction,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ZyProcessAction> queryWrapper = QueryGenerator.initQueryWrapper(zyProcessAction, req.getParameterMap());
		Page<ZyProcessAction> page = new Page<ZyProcessAction>(pageNo, pageSize);
		IPage<ZyProcessAction> pageList = zyProcessActionService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param zyProcessAction
	 * @return
	 */
	@AutoLog(value = "工序动作表-添加")
	@ApiOperation(value="工序动作表-添加", notes="工序动作表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_action:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody ZyProcessAction zyProcessAction) {
		zyProcessActionService.save(zyProcessAction);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param zyProcessAction
	 * @return
	 */
	@AutoLog(value = "工序动作表-编辑")
	@ApiOperation(value="工序动作表-编辑", notes="工序动作表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_action:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody ZyProcessAction zyProcessAction) {
		zyProcessActionService.updateById(zyProcessAction);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工序动作表-通过id删除")
	@ApiOperation(value="工序动作表-通过id删除", notes="工序动作表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_action:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		zyProcessActionService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工序动作表-批量删除")
	@ApiOperation(value="工序动作表-批量删除", notes="工序动作表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:zy_process_action:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zyProcessActionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "工序动作表-通过id查询")
	@ApiOperation(value="工序动作表-通过id查询", notes="工序动作表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<ZyProcessAction> queryById(@RequestParam(name="id",required=true) String id) {
		ZyProcessAction zyProcessAction = zyProcessActionService.getById(id);
		if(zyProcessAction==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(zyProcessAction);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param zyProcessAction
    */
    //@RequiresPermissions("org.jeecg.modules.demo:zy_process_action:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ZyProcessAction zyProcessAction) {
        return super.exportXls(request, zyProcessAction, ZyProcessAction.class, "工序动作表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("zy_process_action:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ZyProcessAction.class);
    }

}
