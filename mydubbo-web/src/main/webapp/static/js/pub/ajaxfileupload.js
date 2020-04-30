jQuery.extend({
	handleError : function(s, xhr, status, e) {
		// If a local callback was specified, fire it
		if (s.error) {
			s.error.call(s.context || s, xhr, status, e);
		}
		// Fire the global callback
		if (s.global) {
			(s.context ? jQuery(s.context) : jQuery.event).trigger("ajaxError", [ xhr, s, e ]);
		}
	},
	createUploadIframe : function(id, uri) {
		var frameId = 'jUploadFrame' + id;
		var io = null;
		if (window.ActiveXObject) {
			if (jQuery.browser.version == "9.0" || jQuery.browser.version == "10.0" || jQuery.browser.version == "11.0") {
				io = document.createElement('iframe');
				io.id = frameId;
				io.name = frameId;
			} else if (jQuery.browser.version == "6.0" || jQuery.browser.version == "7.0" || jQuery.browser.version == "8.0") {
				io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
				if (typeof uri == 'boolean') {
					io.src = 'javascript:false';
				} else if (typeof uri == 'string') {
					io.src = uri;
				}
			}
		} else {
			io = document.createElement('iframe');
			io.id = frameId;
			io.name = frameId;
		}
		io.style.position = 'absolute';
		io.style.top = '-1000px';
		io.style.left = '-1000px';

		document.body.appendChild(io);

		return io;
	},
	createUploadForm : function(id, url, fileElementId, data) {
		// create form
		var formId = 'jUploadForm' + id;
		var fileId = 'jUploadFile' + id;
		var form = jQuery('<form action="' + url + '" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');
		if (data) {
			for ( var i in data) {
				jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
			}
		}

		var oldElement = jQuery('#jQueryFormFile' + fileElementId);
		var newElement = jQuery(oldElement).clone();
		jQuery(oldElement).attr('id', fileId);
		jQuery(oldElement).before(newElement);
		jQuery(oldElement).appendTo(form);

		// set attributes
		jQuery(form).css('position', 'absolute');
		jQuery(form).css('top', '-1200px');
		jQuery(form).css('left', '-1200px');
		jQuery(form).appendTo('body');
		return form;
	},

	ajaxFileUpload : function(s) {
		// TODO introduce global settings, allowing the client to modify them
		// for all requests, not only timeout
		s = jQuery.extend({}, jQuery.ajaxSettings, s);
		var id = new Date().getTime();
		var form = jQuery.createUploadForm(id, s.url, s.fileElementId, s.data);
		var io = jQuery.createUploadIframe(id, s.secureuri);
		var frameId = 'jUploadFrame' + id;
		var formId = 'jUploadForm' + id;
		try {
			var form = $('#' + formId);
			$(form).attr('action', s.url);
			$(form).attr('method', 'POST');
			$(form).attr('target', frameId);
			if (form.encoding) {
				form.encoding = 'multipart/form-data;charset=UTF-8';
			} else {
				form.enctype = 'multipart/form-data;charset=UTF-8';
			}
			$(form).ajaxSubmit({
				url : s.url,
				method : "POST",
				contentType : "text/html",
				dataType : "html",
				success : function(data) {
					var json = eval("(" + data + ")");

					$(".loading", $("#" + s.fileElementId)).hide();
					if ("success" == json.status) {
						// 从服务器返回的json中取出message中的数据
						$("#" + s.fileElementId).attr({
							"fileDesc" : "" || json.result.fileDesc,
							"fileName" : "" || json.result.fileName,
							"fileNail" : "" || json.result.fileNail,
							"status" : json.status
						});
						// 清除
						$("#" + frameId).remove();
						$("#" + formId).remove();
						// 回调
						if (typeof s.callback == 'function') {
							s.callback.call(this, s.fileElementId, json.result.fileDesc, json.result.fileName, json.result.fileNail);
						}
					} else {
						$.messager.alert("提示", json.status, "info");
					}
				}
			});

		} catch (e) {
			//jQuery.handleError(s, xml, null, e);
		}
	}
})
