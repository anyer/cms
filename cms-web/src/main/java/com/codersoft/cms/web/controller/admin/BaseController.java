package com.codersoft.cms.web.controller.admin;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.service.common.BaseService;
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
    @RequestMapping("/toAddPage")
    public String toAddPage() {
        return getAddPagePath();
    }

    /**
     * 添加数据信息
     *
     * @param t 数据对象
     * @return
     */
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
    @RequestMapping("/update")
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
