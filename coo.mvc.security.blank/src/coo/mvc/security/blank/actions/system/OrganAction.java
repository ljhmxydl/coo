package coo.mvc.security.blank.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.message.MessageConfig;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.security.blank.entity.Organ;
import coo.mvc.security.blank.service.SecurityService;
import coo.mvc.util.DwzResultUtils;

/**
 * 机构管理
 */
@Controller("system.organ")
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class OrganAction {

	@Resource
	private SecurityService securityService;
	@Resource
	private MessageConfig messageConfig;

	/**
	 * 查看机构列表。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("organ-list")
	public void list(Model model) {
		model.addAttribute("rootOrgan", securityService.getCurrentUser()
				.getSettings().getDefaultActor().getOrgan());
	}

	/**
	 * 新增机构。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("organ-add")
	public void add(Model model) {
		Organ rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		Organ organ = new Organ();
		organ.setParent(rootOrgan);
		model.addAttribute("parentOrgans", rootOrgan.getOrganTree());
		model.addAttribute("rootOrgan", rootOrgan);
		model.addAttribute(organ);
	}

	/**
	 * 保存机构
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信息。
	 */
	@RequestMapping("organ-save")
	public ModelAndView save(Organ organ) {
		securityService.createOrgan(organ);
		return DwzResultUtils.close(
				messageConfig.getString("organ.add.success"), "organ-list");
	}

	/**
	 * 编辑机构。
	 * 
	 * @param organId
	 *            机构ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("organ-edit")
	public void edit(String organId, Model model) {
		Organ rootOrgan = securityService.getCurrentUser().getSettings()
				.getDefaultActor().getOrgan();
		model.addAttribute("rootOrgan", rootOrgan);
		model.addAttribute("parentOrgans", rootOrgan.getOrganTree());
		model.addAttribute(securityService.getOrgan(organId));
	}

	/**
	 * 更新机构。
	 * 
	 * @param organ
	 *            机构
	 * @return 返回提示信 息。
	 */
	@RequestMapping("organ-update")
	public ModelAndView update(Organ organ) {
		securityService.updateOrgan(organ);
		return DwzResultUtils.refresh(
				messageConfig.getString("organ.edit.success"), "organ-list");
	}

	/**
	 * 删除机构。
	 * 
	 * @param orgnId
	 *            机构ID
	 * @return 返回提示信息。
	 */
	@RequestMapping("organ-delete")
	public ModelAndView delete(String orgnId) {
		securityService.deleteOrgan(orgnId);
		return DwzResultUtils.refresh(
				messageConfig.getString("organ.delete.success"), "organ-list");
	}

}
