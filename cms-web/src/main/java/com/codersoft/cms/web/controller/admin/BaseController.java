package com.codersoft.cms.web.controller.admin;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.service.common.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: BaseController
 * @author: Alex.D
 * @create: 2018-08-03 18:26
 * @description: BaseController
 **/
@Setter
@Getter
@Controller
@Scope("prototype")
@Api(description = "基础请求处理操作")
public class BaseController<T, Long> {

    private String sessionKey;

    private String listPagePath;
    private String addPagePath;
    private String editPagePath;

    private MessageCode successMsg;
    private MessageCode saveFailMsg;
    private MessageCode updateFailMsg;
    private MessageCode deleteFailMsg;

    @Autowired
    private BaseService<T, Long> baseService;

    /**
     * 跳转到数据管理界面
     *
     * @return
     */
    @ApiOperation(value = "跳转到数据管理界面", notes = "公告接口：跳转到数据的管理界面", httpMethod = "GET")
    @RequestMapping("/toListPage")
    public String toListPage() {
        return getListPagePath();
    }

    /**
     * 获取数据列表
     *
     * @param t
     * @return
     */
    @ApiOperation(value = "获取数据列表", notes = "公告接口：根据数据对象获取对应的数据对象集合列表", httpMethod = "POST")
    @ApiImplicitParam(name = "t", value = "数据对象", required = true, dataType = "T")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(T t) {
        return baseService.selectPageList(t);
    }

    /**
     * 跳转到数据添加界面
     *
     * @return
     */
    @ApiOperation(value = "数据添加界面", notes = "公告接口：跳转到对应数据的添加界面")
    @RequestMapping(value = "/toAddPage", method = RequestMethod.GET)
    public String toAddPage() {
        return getAddPagePath();
    }

    /**
     * 添加数据信息
     *
     * @param t 数据对象
     * @return
     */
    @ApiOperation(value = "添加数据信息", notes = "公告接口：添加对应数据信息", httpMethod = "POST")
    @ApiImplicitParam(name = "t", value = "数据对象", required = true, dataType = "T")
    @RequestMapping("/add")
    @ResponseBody
    public ResultMessage addData(@RequestBody T t) {
        int addRes = 0;
        try {
            addRes = baseService.addSelective(t);
            if (addRes == 0) {
                return ResultMessageUtils.returnResultMessage(getSaveFailMsg());
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(getSaveFailMsg(), e.getMessage());
        }
    }

    /**
     * 跳转到数据详情页面
     *
     * @param model
     * @param id 数据ID
     * @return
     */
    @ApiOperation(value = "数据详情页面", notes = "公告接口：跳转到对应数据的详情界面", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "数据ID", required = true, dataType = "Long")
    @RequestMapping("/toDetailPage")
    public String toDetailPage(Model model, @RequestParam("id") Long id) {

        T t = baseService.selectBytId(id);
        model.addAttribute(getSessionKey(), t);
        model.addAttribute("pageFlag", "detail");

        return getEditPagePath();
    }

    /**
     * 跳转到数据修改页面
     *
     * @param model
     * @param id 数据ID
     * @return
     */
    @ApiOperation(value = "数据修改页面", notes = "公告接口：跳转到对应数据的修改界面", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "数据ID", required = true, dataType = "Long")
    @RequestMapping("/toEditPage")
    public String toEditPage(Model model, @RequestParam("id") Long id) {

        T t = baseService.selectBytId(id);
        model.addAttribute(getSessionKey(), t);
        model.addAttribute("pageFlag", "edit");

        return getEditPagePath();
    }

    /**
     * 更新数据信息
     *
     * @param t 数据对象
     * @return
     */
    @ApiOperation(value = "更新数据信息", notes = "公告接口：更新对应数据信息", httpMethod = "POST")
    @ApiImplicitParam(name = "t", value = "数据对象", required = true, dataType = "T")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage updateData(@RequestBody T t) {

        int updateRes = 0;
        try {
            updateRes = baseService.updateByIdSelective(t);
            if (updateRes == 0) {
                return ResultMessageUtils.returnResultMessage(getUpdateFailMsg());
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(getUpdateFailMsg(), e.getMessage());
        }
    }

    /**
     * 删除数据信息
     *
     * @param id 数据ID
     * @return
     */
    @ApiOperation(value = "删除数据信息", notes = "公告接口：删除对应数据信息", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "数据ID", required = true, dataType = "Long")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage deleteData(@RequestParam("id") Long id) {

        int deleteRes = 0;
        try {
            deleteRes = baseService.deleteById(id);
            if (deleteRes == 0) {
                return ResultMessageUtils.returnResultMessage(getDeleteFailMsg());
            }
            return ResultMessageUtils.returnResultMessage(getSuccessMsg());
        } catch (Exception e) {
            return ResultMessageUtils.returnExpectionResultMessage(getDeleteFailMsg(), e.getMessage());
        }
    }
}
