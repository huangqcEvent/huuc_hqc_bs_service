package com.huuc.controller.admin;

import com.huuc.SensitiveWordBsConfig.WordAllow;
import com.huuc.SensitiveWordBsConfig.WordDeny;
import com.huuc.SensitiveWordBsConfig.WordVO;
import com.huuc.dto.SaveWordDTO;
import com.huuc.pojo.SensitiveWordAllow;
import com.huuc.pojo.SensitiveWordDeny;
import com.huuc.result.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Slf4j
@Api(tags = "违禁词管理相关接口")
public class SensitiveWordBsController {

    private final WordAllow wordAllow;
    private final WordDeny wordDeny;


    @ApiOperation(value = "获取违禁词")
    @GetMapping("/getProhibitedWords")
    public AjaxResult getProhibitedWords() {
        return AjaxResult.success(
                WordVO.builder()
                        .whiteList(wordAllow.list())
                        .blackList(wordDeny.list())
                        .build()
        );
    }

    @ApiOperation(value = "删除违禁词(黑名单)")
    @DeleteMapping("/deleteBlackById/{id}")
    public AjaxResult deleteBlackById(@PathVariable String id) {
        if (wordDeny.deleteBlackById(id)){
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.error("删除失败");
    }

    @ApiOperation(value = "删除违禁词(白名单)")
    @DeleteMapping("/deleteWhiteById/{id}")
    public AjaxResult deleteWhiteById(@PathVariable String id) {
        if(wordAllow.deleteWhiteById(id)){
            return AjaxResult.success("删除成功");
        }
        return AjaxResult.success("删除失败");
    }

    @ApiOperation(value = "新增违禁词")
    @PostMapping("/addProhibitedWords")
    public AjaxResult addProhibitedWords(@RequestBody SaveWordDTO saveWordDTO) {
        if (saveWordDTO.getType() == 1){
            if (wordDeny.save(new SensitiveWordDeny(saveWordDTO.getWord()))) {
                return AjaxResult.success("新增成功");
            }
        }else {
            if (wordAllow.save(new SensitiveWordAllow(saveWordDTO.getWord()))) {
                return AjaxResult.success("新增成功");
            }
        }
        return AjaxResult.error("新增失败");
    }
}
