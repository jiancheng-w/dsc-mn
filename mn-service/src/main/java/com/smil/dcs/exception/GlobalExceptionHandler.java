package com.smil.dcs.exception;

import com.smil.commons.response.GlobalResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    private static final String LOG_ERROR = "error-msg:";

    /**
     * 定义要捕获的异常 可以多个 @ExceptionHandler({})
     * 可添加的入参:
     * (HttpServletRequest request, final Exception e, HttpServletResponse response)
     * return GlobalResponse.fail(ExceptionUtils.getStackTrace(e), 500);
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public GlobalResponse<Void> handleException(final Exception e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail("service exception:"+ExceptionUtils.getMessage(e), 500);
    }

    /**
     * 处理Valid校验失败产生的异常
     * <p>
     *     该异常由javax.validation注解产生
     * </p>
     * @param e 异常参数
     * @return 返回给前端的结果对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public GlobalResponse<Void> handleValidException(final MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            sb.append(String.format("field '%s' with error value:[%s], reason: %s",
                    error.getField(), error.getRejectedValue(), error.getDefaultMessage()));
            sb.append(System.lineSeparator());
        });
        return GlobalResponse.fail(sb.toString(), 9999);//TODO need to design the error code overall
    }
    
    @ExceptionHandler(DcsRuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleDcsRuntimeException(final DcsRuntimeException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(e.getMessage(), e.getCode() == null ? 500 : Integer.parseInt(e.getCode()));
    }

    @ExceptionHandler(McRuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleMcRunTimeException(final McRuntimeException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(e.getMessage(), 500);
    }

    @ExceptionHandler(WmsRuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleWmsRunTimeException(final WmsRuntimeException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(e.getMessage(), 500);
    }

    @ExceptionHandler(SapException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleSapException(final SapException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(e.getMessage(), e.getCode() == null ? 500 : Integer.parseInt(e.getCode()));
    }
    
    @ExceptionHandler(ForeignCodeNotFoundException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleForeignCodeNotFoundException(final ForeignCodeNotFoundException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(e.getErrorMessage(), e.getErrorCode());
    }
    
    @ExceptionHandler(LmRuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleLmRuntimeException(final LmRuntimeException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(e.getMessage(), e.getCode());
    }
    
    @ExceptionHandler(SftpRuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleSftpRuntimeException(final SftpRuntimeException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(ExceptionUtils.getMessage(e), e.getCode() == null ? 500 : Integer.parseInt(e.getCode()));
    }
    
    @ExceptionHandler(SysRuntimeException.class)
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public GlobalResponse<Void> handleSysRuntimeException(final SysRuntimeException e) {
        LOG.error(LOG_ERROR, e);
        return GlobalResponse.fail(ExceptionUtils.getMessage(e), e.getCode() == null ? 500 : Integer.parseInt(e.getCode()));
    }
}
