<?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <@std.errorHead><title>会话过期</title></@std.errorHead>
</head>
<body>
    <div class="std-error-panel">
        <div class="head">会话过期</div>
        <div class="body">用户未登录或会话已过期。</div>
        <div class="foot">
            <a href="${ctx}/login">重新登录</a>
        </div>
    </div>
</body>
</html>