package com.allbreak.milk.validation;

import com.allbreak.milk.VO.ResultVO;
import com.allbreak.milk.enums.ResultCode;
import com.allbreak.milk.enums.ResultEnum;
import com.allbreak.milk.utils.ResultVOUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName BasicValidation
 * @Description TODO
 * @Author Lin
 * @Date 2020/7/17 17:23
 * @Version 1.0
 */
public class BasicValidation {

    public static ResultVO checkDataValidate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            if (errors.size() != 0) {
                ObjectError error = errors.get(0);
                return ResultVOUtil.error(400, error.getDefaultMessage());
            }
        }
        return null;
    }

    public static ResultVO checkDataValidate(MultipartFile file) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return ResultVOUtil.error(ResultCode.FAIL.getCode(), ResultEnum.FILE_NOT_NULL.getErroCode(), ResultEnum.FILE_NOT_NULL.getMessage());
        }
        return null;
    }
}
