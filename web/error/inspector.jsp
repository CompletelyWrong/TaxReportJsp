<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<div class="container">
    <div class="body-style">
        <div style="width:45%;position: absolute; left: 13%; margin-top:12% ;">
            <img src="https://lh3.googleusercontent.com/KnAhFB7f8i88GvV9jZbGPMDEKSm8a_Ewz1YGsa7FVhM5ocHarcVq7nAZgpufUmzWmViqU76CbWvP13sh-oLWcjCCfCbvUAO7LOYfoYmtgvC9zST-myYY9AfqePPTMNR_8LVL67T1eT_S923Oi5YrkrtMGkPipUXxqDtdc0Jf9TaC7AI0sQjwaqCKh0YMaQ1Yo9_VgEiwSHB60N1qxfAYXj9g3RrMedThuDOLEh9lJgmvI_VzxRSh7ZEh6hoIeRsHSI55CUE8Buyv1G399y2e_B97LSgJh7d-GU4RJqlrfuRuAWL2x0p_PG52Z83niCdOV41o-z8Kai6z5Il1z8cXnGUvlaFj0P_cfxdvyRIGSpYjTI6LSU2UtCxD-kNgBah7CJVSCpR6AgMHHyQQ5gQYtodvn4C_9GrYKzs1rJn66wNeUfCLJ33aqXWZdW_gILJMXK60rJ_65aCIWHvsAieTqqitZURwH0mXEYqoowrddbKj62pKuB_vOgfkcQWgVuU2jqwEWcg41KZbmfdmAasYTvvRbzSpsG44myVsg-DrVLp7Wrgl2Uii47B-v3aPFYy1_y_2bVthygSryH8xGnPXgsaLFaNWywmZN1hNCavugel_-eyrQn7Elrau1U9ZoZECxL8w7ikuD6fzZf_EWfkFn29ZyLOuRfT8L0bqVrSeInzy6TkNOLV0je8pPyVcgeVJoL0_rQsFZE-Nt_CYutuW1Q4F=w301-h248-no" style="float: right;">
        </div>
        <div style="width:45%;position: absolute; left: 38%;  margin-top:25% ;">
            <h1 style="font-weight:bold;font-size:34px;"><fmt:message key="error.noInspector"/></h1>
            <div>
                <br/>
                <a style="position: relative; left: 24%;"  class="button-style" href="${pageContext.request.contextPath}/"><fmt:message key="inspectorSide.main"/></a>
            </div>
        </div>
    </div>
</div>