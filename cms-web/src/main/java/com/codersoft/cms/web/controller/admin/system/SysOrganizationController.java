package com.codersoft.cms.web.controller.admin.system;

import com.codersoft.cms.common.bean.MessageCode;
import com.codersoft.cms.common.bean.ResultMessage;
import com.codersoft.cms.common.utils.ResultMessageUtils;
import com.codersoft.cms.dao.entity.SysOrganization;
import com.codersoft.cms.service.admin.SysOrganizationService;
import com.codersoft.cms.web.controller.admin.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SysOrganizationController
 * @author: Alex.D
 * @create: 2018-07-12 12:39
 * @description: 组织操作控制类
 **/
@Controller
@RequestMapping("/admin/organization")
public class SysOrganizationController extends BaseController<SysOrganization, Long> {

    public SysOrganizationController() {
        super.setSessionKey("sysOrganization");
        super.setListPagePath("admin/system/organization/organization_list");
        super.setAddPagePath("admin/system/organization/organization_add");
        super.setEditPagePath("admin/system/organization/organization_edit");
        super.setSuccessMsg(MessageCode.SUCCESS);
        super.setSaveFailMsg(MessageCode.ORG_SAVE_FAIL);
        super.setUpdateFailMsg(MessageCode.ORG_UPDATE_FAIL);
        super.setDeleteFailMsg(MessageCode.ORG_DELETE_FAIL);
    }

    @Autowired
    private SysOrganizationService sysOrganizationService;

    /**
     * 验证组织名是否存在
     *
     * @param orgName 组织名称
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkOrgNameIsExist")
    public ResultMessage checkOrgNameIsExist(@RequestParam("orgName") String orgName) {
        SysOrganization sysOrganization = sysOrganizationService.checkOrgNameIsExist(orgName);
        try {
            if (sysOrganization != null) {
                return ResultMessageUtils.returnResultMessage(MessageCode.ORG_NAME_EXIST);
            }
            return ResultMessageUtils.returnResultMessage(MessageCode.SUCCESS);
        } catch (Exception ex) {
            return ResultMessageUtils.returnExpectionResultMessage(MessageCode.ORG_NAME_EXIST, ex.getMessage());
        }
    }
}
