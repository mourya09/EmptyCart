$(document).keydown(function(a){if(a.keyCode==9){a.preventDefault()
}});
$(window).load(function(){$("#rtpanelChart").css({right:-305});
$("#rtpanelAdvsrch").css({right:-305});
$(".poibtn").css({background:"url('resources/images/btn_barpoi_act.png') 0px 0px no-repeat"})
});
function showPanelPOI(){var a=true;
$("#rtpanelChart").animate({right:-305},1000);
$("#rtpanelAdvsrch").animate({right:-305},1000);
$("#rtpanelPOI").animate({right:0},1000);
$(".poibtn").css({background:"url('resources/images/btn_barpoi_act.png') 0px 0px no-repeat"});
$(".chartbtn").css({background:"url('resources/images/btn_barchart.png') 0px 0px no-repeat"});
$(".advsrchbtn").css({background:"url('resources/images/btn_barsearch.png') 0px 0px no-repeat"})
}function hidePanelPOI(){$("#rtpanelPOI").animate({right:-305})
}function showPanelChart(){$("#rtpanelChart").animate({right:0},1000);
$("#rtpanelAdvsrch").animate({right:-305},1000);
$("#rtpanelPOI").animate({right:-305},1000);
$(".chartbtn").css({background:"url('resources/images/btn_barchart_act.png') 0px 0px no-repeat"});
$(".advsrchbtn").css({background:"url('resources/images/btn_barsearch.png') 0px 0px no-repeat"});
$(".poibtn").css({background:"url('resources/images/btn_barpoi.png') 0px 0px no-repeat"})
}function hidePanelChart(){$("#rtpanelPOI").animate({right:-300})
}function showPanelAdSrch(){$("#rtpanelChart").animate({right:-305},1000);
$("#rtpanelAdvsrch").animate({right:0},1000);
$("#rtpanelPOI").animate({right:-305},1000);
$(".advsrchbtn").css({background:"url('resources/images/btn_barsearch_act.png') 0px 0px no-repeat"});
$(".chartbtn").css({background:"url('resources/images/btn_barchart.png') 0px 0px no-repeat"});
$(".poibtn").css({background:"url('resources/images/btn_barpoi.png') 0px 0px no-repeat"})
}function hidePanelAdSrch(){$("#rtpanelAdvsrch").animate({right:-305})
}$(function(){$("#poibtntop").click(function(){showPanelPOI();
$("#resettheme").trigger("click");

$("#catchmentlayer").prop( "checked",true).triggerHandler("click");
})
});
$(function(){$("#chartbtntop").click(function(){showPanelChart()
})
});
$(function(){$("#advsrchbtntop").click(function(){showPanelAdSrch();
$("#resettheme").trigger("click")
})
});
$(function(){$("#tb,#tc,#as").click(function(){var a=$(this).data("iteration")||1;
switch(a){case 1:$(this).parent().parent().animate({right:-276},1000,function(){$("#tb,#tc,#as").removeClass("togglebar");
$("#tb,#tc,#as").addClass("togglebar_rt")
});
break;
case 2:$(this).parent().parent().animate({right:0},1000,function(){$("#tb,#tc,#as").removeClass("togglebar_rt");
$("#tb,#tc,#as").addClass("togglebar")
});
break
}a++;
if(a>2){a=1
}$(this).data("iteration",a)
})
});
$(function(){$("#sh").click(function(){var a=$(this).data("iteration")||1;
switch(a){case 1:$(this).parent().animate({left:-300},1000);
$(this).removeClass("togglebar");
$(this).addClass("togglebar_lt");
break;
case 2:$(this).parent().animate({left:0},1000);
$(this).removeClass("togglebar_lt");
$(this).addClass("togglebar");
break
}a++;
if(a>2){a=1
}$(this).data("iteration",a)
})
});


$(function(){$(".btnmin").off();
$(".btnmin").on("click",function(){if(isClicking){return
}isClicking=true;
$("#btmgridpanel").animate({height:30,width:"70%"},500);
$("#maximize").removeClass("btnrestore");
$("#maximize").addClass("btnmax")
});
isClicking=false
});
$(function(){$ht=$(window).height();
$wt=$(window).width();
$("#maxrest").off();
$("#maxrest").on("click",function(){isClicking=false;
var a=$(this).data("iteration")||1;
switch(a){case 1:$("#btmgridpanel").animate({height:$ht,width:$wt},500);
$(".dn .innertube,  .up .innertube").animate({height:$ht-45},500);
$(".dn .innertube,  .up .innertube").css({overflow:"auto"});
$(this).removeClass("btnmax");
$(this).addClass("btnrestore");
$(".btnmin").click(function(){if(isClicking){return
}isClicking=true;
$("#btmgridpanel").animate({height:30,width:"70%"},500);
$("#maximize").removeClass("btnrestore");
$("#maximize").addClass("btnmax")
});
break;
case 2:$("#btmgridpanel").animate({height:230,width:"70%"},500);
$(".dn .innertube,  .up .innertube").animate({height:185},500);
$(".dn .innertube,  .up .innertube").css({overflow:"auto"});
$(this).removeClass("btnrestore");
$(this).addClass("btnmax");
$(".btnmin").click(function(){if(isClicking){return
}isClicking=true;
$("#btmgridpanel").animate({height:30,width:"70%"},500);
$("#maximize").removeClass("btnrestore");
$("#maximize").addClass("btnmax")
});
break
}a++;
if(a>2){a=1
}$(this).data("iteration",a)
})
});
$(function(){$(".btnclose").click(function(){$("#btmgridpanel").hide()
})
});


// code for macroview panel

$(function(){$(".btnmin2").off();
$(".btnmin2").on("click",function(){if(isClicking){return
}isClicking=true;
$("#dwntblpanel").animate({height:30,width:"70%"},500);
$("#maximize2").removeClass("btnrestore2");
$("#maximize2").addClass("btnmax2")
});
isClicking=false
});
$(function(){$ht=$(window).height();
$wt=$(window).width();
$("#maxrest2").off();
$("#maxrest2").on("click",function(){isClicking=false;
var a=$(this).data("iteration")||1;
switch(a){case 1:$("#dwntblpanel").animate({height:$ht,width:$wt},500);
$(".dn2 .innertube,  .up2 .innertube").animate({height:$ht-45},500);
$(".dn2 .innertube,  .up2 .innertube").css({overflow:"auto"});
$(this).removeClass("btnmax2");
$(this).addClass("btnrestore2");
$(".btnmin2").click(function(){if(isClicking){return
}isClicking=true;
$("#dwntblpanel").animate({height:30,width:"70%"},500);
$("#maximize2").removeClass("btnrestore2");
$("#maximize2").addClass("btnmax2")
});
break;
case 2:$("#dwntblpanel").animate({height:230,width:"70%"},500);
$(".dn2 .innertube,  .up2 .innertube").animate({height:185},500);
$(".dn2 .innertube,  .up2 .innertube").css({overflow:"auto"});
$(this).removeClass("btnrestore2");
$(this).addClass("btnmax2");
$(".btnmin2").click(function(){if(isClicking){return
}isClicking=true;
$("#dwntblpanel").animate({height:30,width:"70%"},500);
$("#maximize2").removeClass("btnrestore2");
$("#maximize2").addClass("btnmax2")
});
break
}a++;
if(a>2){a=1
}$(this).data("iteration",a)
})
});
$(function(){$(".btnclose2").click(function(){$("#dwntblpanel").hide()
})
});


