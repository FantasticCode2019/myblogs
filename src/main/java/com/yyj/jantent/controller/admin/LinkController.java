package com.yyj.jantent.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.yyj.jantent.controller.AbstractController;
import com.yyj.jantent.controller.helper.ExceptionHelper;
import com.yyj.jantent.dto.Types;
import com.yyj.jantent.exception.TipException;
import com.yyj.jantent.modal.bo.RestResponseBo;
import com.yyj.jantent.modal.vo.MetaVo;
import com.yyj.jantent.service.IMetaService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tangj
 * @date 2018/2/1 21:32
 */
@Controller
@RequestMapping("admin/links")
public class LinkController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    @Resource
    private IMetaService metaService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        List<MetaVo> metas = metaService.getMetas(Types.LINK.getType());
        request.setAttribute("links", metas);
        return "admin/links";

    }

    @PostMapping(value = "save")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo saveLink(@RequestParam String title, @RequestParam String url,
                                   @RequestParam String logo, @RequestParam Integer mid,
                                   @RequestParam(value = "sort", defaultValue = "0") int sort) {
        try {
            MetaVo metas = new MetaVo();
            metas.setName(title);
            metas.setSlug(url);
            metas.setDescription(logo);
            metas.setSort(sort);
            metas.setType(Types.LINK.getType());
            if (null != mid) {
                metas.setMid(mid);
                metaService.update(metas);
            } else {
                metaService.saveMeta(metas);
            }
        } catch (Exception e) {
            String msg = "友链保存失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam int mid) {
        try {
            metaService.delete(mid);
        } catch (Exception e) {
            String msg = "友链删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }
}
