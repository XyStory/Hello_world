<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script>
        
        function save() {

            var deptId = $("select[name=deptId]").val();
            var jobId = $("select[name=jobId]").val();
            var employeeName=$("input[name=employeeName]").val();
            var cardId = $("input[name=cardId]").val();
            var address = $("input[name=address]").val();
            var postCode = $("input[name=postCode]").val();
            var tel = $("input[name=tel]").val();
            var phone = $("input[name=phone]").val();
            var qqNum = $("input[name=qqNum]").val();
            var email = $("input[name=email]").val();
            var sex = $("select[name=sex]").val();
            var party = $("input[name=party]").val();
            var birthday = $("input[name=birthday]").val();
            var race = $("input[name=race]").val();
            var education = $("input[name=education]").val();
            var speciality = $("input[name=speciality]").val();
            var hobby = $("input[name=hobby]").val();
            var remark = $("textarea[name=remark]").val();


            console.log(remark);

            var jsondata={"deptId":deptId, "jobId":jobId, "employeeName":employeeName, "cardId":cardId, "address":address,
                "postCode":postCode, "tel":tel, "phone":phone, "qqNum":qqNum, "email":email, "sex":sex, "party":party,
                "birthday":birthday, "race":race, "education":education, "speciality":speciality, "hobby":hobby, "remark":remark};

            $.ajax({
                url:"${ctx}/save-employee",
                type: "post",
                data: jsondata,
                dataType:"json",
                success:function (data) {

                    console.log(data);

                    if(data=='1') {
                        alert("????????????");
                        window.location.href = "${ctx}/show-employee";
                    }else {
                        alert("????????????");
                    }
                }
            })
        }

    </script>
</head>
<body>
<div class="panel panel-default"></div>
<form class="form-horizontal">
    <div class="form-group">
        <label for="selectDept" class="col-sm-1 control-label">????????????</label>
        <div class="col-sm-4">
            <select id="selectDept" name="deptId" class="form-control">
                <option value="0">--?????????????????????--</option>
                <c:forEach items="${deptList}" var="dept">
                    <option value="${dept.deptId}">${dept.name}</option>
                </c:forEach>
            </select>
        </div>
        <label for="selectJob" class="col-sm-1 control-label">?????????</label>
        <div class="col-sm-4">
            <select id="selectJob" name="jobId" class="form-control">
                <option value="0">--???????????????--</option>
                <c:forEach items="${jobs}" var="job">
                    <option value="${job.jobId}">${job.jobName}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="inputName" class="col-sm-1 control-label">?????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputName" placeholder="name" name="employeeName" required>
        </div>
        <label for="inputCardId" class="col-sm-1 control-label">??????????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputCardId" name="cardId" required>
        </div>
    </div>
    <div class="form-group">
        <label for="inputAddress" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputAddress" name="address" required>
        </div>
        <label for="inputPostCode" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputPostCode" name="postCode">
        </div>
    </div>
    <div class="form-group">
        <label for="inputTel" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputTel" name="tel">
        </div>
        <label for="inputPhone" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputPhone" name="phone" required>
        </div>
    </div>
    <div class="form-group">
        <label for="inputQQNum" class="col-sm-1 control-label">QQ?????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputQQNum" name="qqNum">
        </div>
        <label for="inputEmail" class="col-sm-1 control-label">E-mail???</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputEmail" name="email" required>
        </div>
    </div>
    <div class="form-group">
        <label for="selectSex" class="col-sm-1 control-label">QQ?????????</label>
        <div class="col-sm-4">
            <select id="selectSex" name="sex" class="form-control">
                <option value="0">--???????????????--</option>
                <option value="1">???</option>
                <option value="2">???</option>
            </select>
        </div>
        <label for="inputParty" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputParty" name="party">
        </div>
    </div>
    <div class="form-group">
        <label for="inputBirthday" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="date" class="form-control" id="inputBirthday" name="birthday">
        </div>
        <label for="inputRace" class="col-sm-1 control-label">?????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputRace" name="race">
        </div>
    </div>
    <div class="form-group">
        <label for="inputEducation" class="col-sm-1 control-label">?????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputEducation" name="education">
        </div>
        <label for="inputSpeciality" class="col-sm-1 control-label">?????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputSpeciality" name="speciality">
        </div>
    </div>
    <div class="form-group">
        <label for="inputHobby" class="col-sm-1 control-label">???????????????</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="inputHobby" name="hobby">
        </div>
    </div>
    <div class="form-group">
        <label for="inputRace" class="col-sm-1 control-label">?????????</label>
        <div class="col-sm-9">
            <textarea id="textareaRemark" class="form-control" rows="4" name="remark"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-1 col-sm-10">
            <button type="button" class="btn btn-default" onclick="save()">??????</button>
        </div>
    </div>
</form>
</body>
</html>
