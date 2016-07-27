// JavaScript Document

function check()
{
    
    if(confirm("ȷ����?"))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function getCookie(name) {
  var cookieValue = null;
    if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = $.trim(cookies[i]);
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) == (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}
/*===================================================================================================================*/
/*===================================================================================================================*/
/*==========================================plugin plugin plugin=====================================================*/
/*===================================================================================================================*/
/*===================================================================================================================*/
var ojPage = (function($){
    var gConf;
    //����Ĳ��ֻ���ڳ�ʼ����ʱ�򱻴���һ�Σ����Ժ��ojPage�ĵ����У������ٴδ�������,�����Բ���Ҫ�жϲ���ǲ�����$��
    $.fn.pagination = function(conf){
        gConf = conf;
        var page = this.ojPaginate(conf);
        var startPage = page[0];
        var endPage = page[1];
        var inhtml = this.ojFormat(conf, startPage, endPage);
        $.each(this, function(i, obj){
            $(obj).html(inhtml);
        });
    };
    $.fn.ojPaginate = function(conf){
        var startPage, endPage;

        if(conf.span == undefined) conf.span = 9;
        conf.span = conf.span - 1;

        if (conf.page > 5 && conf.max - conf.span > 0){
            startPage = conf.page - 4;
            if (startPage > conf.max - conf.span){
                startPage = conf.max - conf.span;    
            }
        }else startPage = 1;

        endPage = startPage + conf.span;
        if(endPage > conf.max) endPage = conf.max;

        return [startPage, endPage];
    };
    $.fn.ojFormat = function(conf, startPage, endPage){
        var ul = tag('ul');
        if(conf.page == 1){
            ul.push(tag('li',tag('a', '<<')).attr('class', 'disabled')).
            push(tag('li',tag('a', '<')).attr('class', 'disabled'));
        }else{
            ul.push(tag('li',tag('a', '<<').attr('href', '#').attr('onclick', 'ojPage.paging($(this).html()); return false'))).
            push(tag('li',tag('a','<').attr('href', '#').attr('onclick', 'ojPage.paging($(this).html()); return false;')));
        }

        for(var i=startPage; i<=endPage; i++){
            if (i == conf.page) ul.push(tag('li',tag('a', i)).attr('class','active'));
            else ul.push(tag('li',tag('a', i).attr('href', '#').attr('onclick', 'ojPage.paging($(this).html());return false;')));
        }

        if(conf.page == conf.max){
            ul.push(tag('li',tag('a','>')).attr('class', 'disabled')).
            push(tag('li',tag('a', '>>')).attr('class','disabled'));
        }else{
            ul.push(tag('li',tag('a', '>').attr('href', '#').attr('onclick', 'ojPage.paging($(this).html()); return false;'))).
            push(tag('li',tag('a', '>>').attr('href', '#').attr('onclick', 'ojPage.paging($(this).html()); return false;')));
        }

        return tag('div').attr('class','pagination').push(ul).toString();
    };
    return {
        paging: function(val){
            if (val == '&lt;') gConf.callback(gConf.page-1, gConf.type_, gConf.order);
            else if(val == '&gt;') gConf.callback(gConf.page+1, gConf.type_, gConf.order);
            else if(val == '&lt;&lt;') gConf.callback((gConf.page=1), gConf.type_, gConf.order);
            else if(val == '&gt;&gt;') gConf.callback((gConf.page=gConf.max), gConf.type_, gConf.order);
            else gConf.callback(parseInt(val), gConf.type_, gConf.order);
        }
    }
})(jQuery);
/*===================================================================================================================*/
/*===================================================================================================================*/
/*==========================================tag tag tag tag tag======================================================*/
/*===================================================================================================================*/
/*===================================================================================================================*/
var tag = (function(){
    var Tag = function(tagName, tagAttrs, single){
        var _tagName, _tagAttrs, _single, _brother=[], _childTag=[];
        _tagName = tagName;
        _tagAttrs = tagAttrs;
        _single = single;
        return {
            isMe: function(){},
            attr: function(key, value){
                if(value === undefined) return _tagAttrs[key];
                else _tagAttrs[key] = value;
                return this;
            },
            push: function(inserted_tag){
                _childTag.push(inserted_tag);
                return this;
            },
            unshift: function(inserted_tag){
                _childTag.unshift(inserted_tag);
                return this;
            },
            concat: function(inserted_tag){
                _brother = _brother.concat(inserted_tag);
                return this;
            },
            toString: function(){//selfHtml
                var arr = new Array();
                var tmp = new Array();
                var arrlen = 0;
                arr[arrlen++] = '<';
                arr[arrlen++] = _tagName;
                for (var key in _tagAttrs){
                    tmp[0] = ' ';
                    tmp[1] = key;
                    tmp[2] = '="';
                    tmp[3] = _tagAttrs[key];
                    tmp[4] = '"';
                    arr[arrlen++] = tmp.join('');
                }

                arr[arrlen++] = _single ? ' />' : '>';
                // if (!Array.prototype.filter) {
                //     alert("no map");
                // }
                // alert(_childTag);
                jQuery.map(_childTag, function(inserted_tag){
                    if(inserted_tag!=undefined) arr[arrlen++] = inserted_tag.toString();
                })
                // _childTag.map();

                if(!_single){
                    arr[arrlen++] = '</';
                    arr[arrlen++] = _tagName;    
                    arr[arrlen++] = '>';
                }

                jQuery.map(_brother, function(concated_tag){
                    if(concated_tag!=undefined) arr[arrlen++] = concated_tag.toString();
                })
                // _brother.map(function(concated_tag){
                //     if(concated_tag!=undefined) arr[arrlen++] = concated_tag.toString();
                // });
                return arr.join('');
            },
            html: function() {//childHtml
                var arr = new Array();
                var arrlen = 0;
                jQuery.map(_childTag, function(inserted_tag){
                    arr[arrlen++] = inserted_tag.toString();
                })
                // _childTag.map(function(inserted_tag){
                //     arr[arrlen++] = inserted_tag.toString();
                // });
                return arr.join('');
            }
        }
    };
    return function(tagName){
        var attrs = {}, tObj, i=1;
        if(arguments[1]!='s'){//is not a single tag
            if(arguments[1] instanceof Object){
                try{
                    arguments[1].isMe();
                }catch(e){
                    attrs = arguments[1];
                    i = 2;
                }
            }
            tObj = Tag(tagName, attrs);
        }else{ // is a single tag
            i = 2;
            if(arguments[1] instanceof Object){ //arguments[1] is {} object
                try{
                    arguments[1].isMe();
                }catch(e){
                    attrs = arguments[2];
                    i = 3;
                }
            }
            tObj = Tag(tagName, attrs, true);
        }
        for(; i<arguments.length; i++) {
            tObj.push(arguments[i]);
        }
        return tObj;
    };
})();
/*============================================verify iniformation=================================================*/
function isEmail(fData){
    var emailreg = new RegExp("^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+(\.com|\.cn)$");
    return emailreg.test(fData);
}
function isUsername(fData){
    var usernamereg =  /^[\u4E00-\u9FA5]+$/; 
    return usernamereg.test(fData)
}
function isStudentID(fData){
    var stuIDpatt = /^[0-9]*$/;
    return stuIDpatt.test(fData);
}
function isStudentQQ(fData){
    var stuIDpatt = /^[0-9]*$/ ;
    return stuIDpatt.test(fData);
}
function isPassword(fData) {
    var password = new RegExp("^[a-zA-Z0-9._-]{5,20}$");
    return password.test(fData)
}
function isIdCard(fData){
    var idcard = /^\d{15}|\d{}18$/;
    return idcard.test(fData);

}
function checkout_password(password, password_again) {
    if(!password) {return false;}
    if (password === password_again) {
        return true;
    }
    return false;
}
function register_ajax(){
	var username = $("#student_name").val();
	var student_id = $("#student_id").val();
	var student_degree = $("#student_degree").val();
	var student_nation = $("#student_nation").val();
	var student_place = $("#student_place").val();
	var student_qq = $("#student_qq").val();
	var student_email = $("#student_email").val();
	var student_bedroom = $("#student_bedroom").val();
	var student_class = $("#student_class").val();
	var student_idcard = $("#student_idcard").val();
	var student_bdate = $("#student_bdate").val();
	var student_polit_stat = $("#student_polit_stat").val();
	var student_enrol_date = $("#student_enrol_date").val();
	var student_tel = $("#student_tel").val();
    if(!isUsername(username) || username.length <2){
        $("#register_error").addClass("error_color").html("�û��������Ǵ�����λ�����ģ�����д����ʵ������"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentname").addClass("has-error");
        return false
    }$("#studentname").removeClass("has-error");
    if (!isStudentID(student_id) || student_id.length >16 || student_id.length <8){
        $("#register_error").addClass("error_color").html("����ʵ��д���ѧ�ţ���λ����"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentid").addClass("has-error");
        return false
    }$("#studentid").removeClass("has-error");
    if (student_degree == ''){
        $("#register_error").addClass("error_color").html("��������Դ��"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentdegree").addClass("has-error");
        return false
    }$("#studentdegree").removeClass("has-error");
    if (student_nation == ''){
        $("#register_error").addClass("error_color").html("��������Դ��"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentnation").addClass("has-error");
        return false
    }$("#studentnation").removeClass("has-error");
    if (student_place == ''){
        $("#register_error").addClass("error_color").html("��������Դ��"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentplace").addClass("has-error");
        return false
    }$("#studentplace").removeClass("has-error");
    if (!isStudentQQ(student_qq) || student_qq.length < 5){
        $("#register_error").addClass("error_color").html("����ʵ��д���QQ,ȫ��Ϊ����"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentqq").addClass("has-error");
        return false
    }$("#studentqq").removeClass("has-error");
    if (!isEmail(student_email) || student_email == ""){
        $("#register_error").addClass("error_color").html("��������ȷ������"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentemail").addClass("has-error");
        return false
    }$("#studentemail").removeClass("has-error");
    if (student_bedroom == ""){
        $("#register_error").addClass("error_color").html("��������ȷ�����Һ�"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentbedroom").addClass("has-error");
        return false
    }$("#studentbedroom").removeClass("has-error");
    if (student_class == ""){
        $("#register_error").addClass("error_color").html("��������ȷ�����Һ�"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentclass").addClass("has-error");
        return false
    }$("#studentclass").removeClass("has-error");
    if (student_class == ""){
        $("#register_error").addClass("error_color").html("��������ȷ�����Һ�"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentclass").addClass("has-error");
        return false
    }$("#studentclass").removeClass("has-error");
    if (!isIdCard(student_idcard)){
        $("#register_error").addClass("error_color").html("��������ȷ�����֤15/18"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentidcard").addClass("has-error");
        return false
    }$("#studentidcard").removeClass("has-error");
    if (!isIdCard(student_idcard)){
        $("#register_error").addClass("error_color").html("��������ȷ�����֤15/18"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentidcard").addClass("has-error");
        return false
    }$("#studentidcard").removeClass("has-error");
    if (student_polit_stat == ""){
        $("#register_error").addClass("error_color").html("��������ȷ�����֤15/18"
            ).fadeIn(1000).fadeOut(4000);
        $("#student_politstat").addClass("has-error");
        return false
    }$("#student_politstat").removeClass("has-error");
    if (!isStudentQQ(student_tel) || student_tel.length != 11){
        $("#register_error").addClass("error_color").html("������ò����Ϊ��"
            ).fadeIn(1000).fadeOut(4000);
        $("#studenttel").addClass("has-error");
        return false
    }$("#studenttel").removeClass("has-error");
    if (student_enrol_date == ""){
        $("#register_error").addClass("error_color").html("������ò����Ϊ��"
            ).fadeIn(1000).fadeOut(4000);
        $("#student_enroldate").addClass("has-error");
        return false
    }$("#student_enroldate").removeClass("has-error");    
    if (student_bdate == ""){
        $("#register_error").addClass("error_color").html("������ò����Ϊ��"
            ).fadeIn(1000).fadeOut(4000);
        $("#studentbdate").addClass("has-error");
        return false
    }$("#studentbdate").removeClass("has-error");
	msg = {};
	msg.username = username;
	msg.student_id = student_id;
	msg.student_degree = student_degree;
	msg.student_nation = student_nation;
    msg.student_place = student_place;
    msg.student_qq = student_qq;
    msg.student_email = student_email;
    msg.student_bedroom = student_bedroom;
    msg.student_class = student_class;
    msg.student_idcard = student_idcard;
    msg.student_bdate = student_bdate;
    msg.student_polit_stat = student_polit_stat;
    msg.student_enrol_date = student_enrol_date;
    msg.student_tel = student_tel;
    msg.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post("/web/front/register/",msg,function(data){
    	$("#register_form").submit();
    });

}
function user_login_ajax(){
    var username = $("#username").val();
    var password = $("#password").val();
    msg = {};
    msg.username = username;
    msg.password = password;
    msg.csrfmiddlewaretoken = getCookie("csrftoken");
    alert("ss");
    $.post("/user/login_ajax/",msg,function(data){
        if(data.canlogin){
            $("#login_form").submit();
        }else{
            alert('error!');
        }
    });
}

function show_user_list(data){
    var h = tag('tr');
    var i = 1;
    $.each(data.infor, function(i, information){
        j = i +1;
        h.push(tag('tr',
                tag('td',j),
                tag('td', 
                    tag("a",information.username).attr('href',"/user/information/"+information.pk)),
                tag('td', information.student_id),
                tag('td', information.student_tel)
            ));
    });
    $('#user_list').html(h.html());
}
function get_user_list(page){
    get_content = {};
    if(!isNaN(page)) get_content.page = page;
    $.get('/user/user_list_ajax/', get_content, function(data){
        show_user_list(data);
        $('#page_area').pagination(
        {
            'max': data.max,
            "page": data.page,
            'callback':get_user_list
        });
    });
}
function user_search_ajax(page){
    get_content = {};
    get_content.control = $("#student_name_search").val();
    if(!isNaN(page)) get_content.page = page;
    $.get('/user/search_user_ajax/', get_content, function(data){
        show_user_list(data);
        $('#page_area').pagination(
        {
            'max': data.max,
            "page": data.page,
            'callback':user_search_ajax
        });
    });
}
function change_user_information_ajax(){
    get_content = {};
    get_content.student_degree = $("#student_degree").val();
    get_content.student_qq = $("#student_qq").val();
    get_content.student_email = $("#student_email").val();
    get_content.student_class = $("#student_class").val();
    get_content.student_polit_stat = $("#student_polit_stat").val();
    get_content.student_tel = $("#student_tel").val();
    get_content.dad_name = $("#dad_name").val();
    get_content.student_mom_name = $("#student_mom_name").val();
    get_content.dad_tel = $("#dad_tel").val();
    get_content.mom_tel = $("#mom_tel").val();
    get_content.dad_mailplace = $("#dad_mailplace").val();
    get_content.mom_mailplace = $("#mom_mailplace").val();
    get_content.student_hobby = $("#student_hobby").val();
    get_content.student_school_job = $("#student_school_job").val();
    get_content.student_motto = $("#student_motto").val();
    get_content.student_bank_num = $("#student_bank_num").val();
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/user/change_user_information_ajax/',get_content,function(data){
        $("#change_form").submit();
        alert('ok!');
    });
}
function add_option(){
    var p_element = $("#student_class");
    var times = p_element.attr("alt")-2010+1;
    var grade = ["Ӧ������","��ѧ��Ӧ����ѧ","��Ϣ������ѧ","�����Ϣ��ѧ�빤��"];
    for (num in grade){
        for(var i =1;i<= times;i++){
           p_element.append("<option>"+grade[num]+(1101)+"</option>");
        }
        p_element.append("<hr>");

    }
}
function delete_class_ajax(){
    var get_content = {};
    var classlists = [];
    var flag = 0;
    var form = document.getElementById('delete_form');
    var box = form.getElementsByTagName('input');
    for(var i = 0; i < box.length; i++){
       if(box[i].type == 'checkbox' && box[i].checked){
        classlists[flag] = box[i].value;
        flag = flag + 1;
       }
    }
    get_content.delete_list = classlists;
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/teacher/delete_class_ajax/',get_content,function(data){
        if(data.candelete){
            alert("successfully!");
            $("#delete_form").submit();
        }else{
            alert("error!");
            return false;
        }
    });
}
function add_class_ajax(){
    var get_content = {};
    var classlists = [];
    var flag = 0;
    var form = document.getElementById('add_form');
    var box = form.getElementsByTagName('input');
    for(var i = 0; i < box.length; i++){
       if(box[i].type == 'checkbox' && box[i].checked){
        classlists[flag] = box[i].value;
        flag = flag + 1;
       }
    }
    get_content.add_list = classlists;
    alert(classlists);
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/teacher/add_class_ajax/',get_content,function(data){
        if(data.candelete){
            alert("successfully!");
            $("#add_form").submit();
        }else{
            alert("error!");
            return false;
        }
    });
}
function teacher_change_user_information_ajax(){
    get_content = {};
    get_content.user_pk = $("#name_pk").attr("alt");
    alert(get_content.user_pk);
    get_content.student_degree = $("#student_degree").val();
    get_content.student_qq = $("#student_qq").val();
    get_content.student_name = $("#student_name").val();
    get_content.student_id = $("#student_id").val();
    get_content.student_email = $("#student_email").val();
    get_content.student_class = $("#student_class").val();
    get_content.student_polit_stat = $("#student_polit_stat").val();
    get_content.student_tel = $("#student_tel").val();
    get_content.dad_name = $("#dad_name").val();
    get_content.student_mom_name = $("#student_mom_name").val();
    get_content.student_nation = $("#student_nation").val();
    get_content.student_place = $("#student_place").val();
    get_content.dad_tel = $("#dad_tel").val();
    get_content.mom_tel = $("#mom_tel").val();
    get_content.dad_mailplace = $("#dad_mailplace").val();
    get_content.student_idcard = $("#student_idcard").val();
    get_content.student_bedromm = $("#student_bedromm").val();
    get_content.mom_mailplace = $("#mom_mailplace").val();
    get_content.student_hobby = $("#student_hobby").val();
    get_content.student_birthday = $("#student_birthday").val();
    get_content.student_school_job = $("#student_school_job").val();
    get_content.student_enrol_date = $("#student_enrol_date").val();
    get_content.student_motto = $("#student_motto").val();
    get_content.student_bank_num = $("#student_bank_num").val();
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/teacher/teacher_change_user_information_ajax/',get_content,function(data){
        $("#teacher_change_form").submit();
        alert('ok!');
    });
}
function evaluation_ajax(){ //�ۺ�����ģ��
    get_content = {};
    get_content.user_pk = $("#name_pk").attr("alt");
    get_content.year_num = $("#year_num").val();
    get_content.intelligence = $("#intelligence").val();
    get_content.ideology = $("#ideology").val();
    get_content.ability = $("#ability").val();
    get_content.mind = $("#mind").val();
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/teacher/evaluation_ajax/',get_content,function(data){
        $("#evaluation_form").submit();
        alert('ok!');
    });
}


function teacher_valuation_ajax(){ //��ʦ����ģ��
    get_content = {};
    get_content.pk = $("#name_pk").attr("alt");   //ѧ��ѧ��
    get_content.course = $("#course_name").val();  //�γ�����
    get_content.comments_time = $("#course_time").val(); //�γ�ʱ��
    get_content.teacher_comments = $("#teacher_course_valuation").val(); //�γ���ʦ������
    //a = $("#teacher_course_valuation");
    //alert(a);
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/teacher/teacher_valuation_ajax/',get_content,function(data){
        $("#teacher_valuation_form").submit();
        alert('ok!');
    });

}

function counselor_valuation_ajax(){ //����Ա����ģ��
    get_content = {};
    get_content.pk = $("#name_pk").attr("alt");   //ѧ��ѧ��
    get_content.counselor_comments = $("#counselor_course_valuation").val(); //����Ա������
    // a = $("#counselor_course_valuation");
    //alert(a);
    get_content.csrfmiddlewaretoken =  getCookie('csrftoken');
    $.post('/teacher/counselor_valuation_ajax/',get_content,function(data){
        $("#counselor_valuation_form").submit();
        alert('ok!');
    });

}