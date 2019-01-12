(function ($, $document) {
  "use strict";
  $(document).on('foundation-contentloaded', function(e) {
  });
  $document.on("dialog-ready", function(e) {
    var dialog = $('.cq-dialog');
    var componentPath = dialog.attr('action').replace('_jcr_content', 'jcr:content');
    $.ajax({
      url:componentPath+'.json',
      type:'GET',
      dataType:'json',
      async: true,
      success: function(resp) {
        var myObject = {};
        if('channelLogoPath' in resp) {
          myObject.channelLogoPath = resp.channelLogoPath;
        }
        if('planOne' in resp) {
          myObject.planOne = resp.planOne;
        }
        if('classificationOne' in resp) {
          myObject.classificationOne = resp.classificationOne;
        }
        if('classificationTwo' in resp) {
          myObject.classificationTwo = resp.classificationTwo;
        }
        if('classificationThree' in resp) {
          myObject.classificationThree = resp.classificationThree;
        }
        if('planOnePackageOne' in resp) {
          myObject.packageOne = resp.planOnePackageOne;
        }
        if('planOnePackageTwo' in resp) {
          myObject.packageTwo = resp.planOnePackageTwo;
        }
        if('planOnePackageThree' in resp) {
          myObject.packageThree = resp.planOnePackageThree;
        }
        if('planTwo' in resp) {
          myObject.planTwo = resp.planTwo;
        }
        if('planTwoPackageOne' in resp) {
          myObject.packageFour = resp.planTwoPackageOne;
        }
        if('planTwoPackageTwo' in resp) {
          myObject.packageFive = resp.planTwoPackageTwo;
        }
        if('planTwoPackageThree' in resp) {
          myObject.packageSix = resp.planTwoPackageThree;
        }
        if('planThree' in resp) {
          myObject.planThree = resp.planThree;
        }
        if('planThreePackageOne' in resp) {
          myObject.packageSeven = resp.planThreePackageOne;
        }
        if('planThreePackageTwo' in resp) {
          myObject.packageEight = resp.planThreePackageTwo;
        }
        if('planThreePackageThree' in resp) {
          myObject.packageNine = resp.planThreePackageThree;
        }
        $.ajax({
          url:'/bin/dynamicPackages?items='+ JSON.stringify(myObject),
          contentType: 'application/json; charset=utf-8',
          type:'GET',
          dataType:'json',
          async: true,
          success: function(data) {
            console.log(JSON.stringify(data));
            var x = $("[name='./planOnePackageOne']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planOnePackageOne']").next();
            ulElement.find('li').remove().end();
            var i = 0;
            $.each(data.packageOne, function (k, v) {
              if(i ==0) {
                $("[name='./planOnePackageOne']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planOnePackageOne']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              i++;
            });
            var x = $("[name='./planOnePackageTwo']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planOnePackageTwo']").next();
            ulElement.find('li').remove().end();
            var j = 0;
            $.each(data.packageTwo, function (k, v) {
              if(j ==0) {
                $("[name='./planOnePackageTwo']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planOnePackageTwo']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              j++;
            });
            var x = $("[name='./planOnePackageThree']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planOnePackageThree']").next();
            ulElement.find('li').remove().end();
            var l = 0;
            $.each(data.packageThree, function (k, v) {
              if(l ==0) {
                $("[name='./planOnePackageThree']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planOnePackageThree']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              l++;
            });
            var x = $("[name='./planTwoPackageOne']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planTwoPackageOne']").next();
            ulElement.find('li').remove().end();
            var m = 0;
            $.each(data.packageFour, function (k, v) {
              if(m ==0) {
                $("[name='./planTwoPackageOne']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planTwoPackageOne']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              m++;
            });
            var x = $("[name='./planTwoPackageTwo']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planTwoPackageTwo']").next();
            ulElement.find('li').remove().end();
            var n = 0;
            $.each(data.packageFive, function (k, v) {
              if(n ==0) {
                $("[name='./planTwoPackageTwo']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planTwoPackageTwo']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              n++;
            });
            var x = $("[name='./planTwoPackageThree']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planTwoPackageThree']").next();
            ulElement.find('li').remove().end();
            var o = 0;
            $.each(data.packageSix, function (k, v) {
              if(o ==0) {
                $("[name='./planTwoPackageThree']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planTwoPackageThree']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              o++;
            });
            var x = $("[name='./planThreePackageOne']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planThreePackageOne']").next();
            ulElement.find('li').remove().end();
            var p = 0;
            $.each(data.packageSeven, function (k, v) {
              if(p ==0) {
                $("[name='./planThreePackageOne']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planThreePackageOne']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              p++;
            });

            var x = $("[name='./planThreePackageTwo']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planThreePackageTwo']").next();
            ulElement.find('li').remove().end();
            var q = 0;
            $.each(data.packageEight, function (k, v) {
              if(q ==0) {
                $("[name='./planThreePackageTwo']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planThreePackageTwo']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              q++;
            });

            var x = $("[name='./planThreePackageThree']").closest(".coral-Select").find('option').remove().end();
            var ulElement = $("[name='./planThreePackageThree']").next();
            ulElement.find('li').remove().end();
            var r = 0;
            $.each(data.packageNine, function (k, v) {
              if(r ==0) {
                $("[name='./planThreePackageThree']").prev().find(".coral-Select-button-text").text(v);
              }
              var pcVar = $("[name='./planThreePackageThree']")[0];
              $("<option>").appendTo(pcVar).val(k).html(v);
              $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(ulElement).html(v);
              r++;
            });
          },
          error: function(jqXHR, textStatus, errorThrown) {
          }
        });
      },
      error: function(jqXHR, textStatus, errorThrown) {
      }
    });
  });

  $(document).on('selected.select', '.classificationOne-class', function(e) {
    $("[name='./planOne']").prev().find(".coral-Select-button-text").text("Please Select a Plan");
    $("[name='./planOnePackageOne']").prev().find(".coral-Select-button-text").text("Please Select a Package");
    $("[name='./planOnePackageTwo']").prev().find(".coral-Select-button-text").text("Please Select a Package");
    $("[name='./planOnePackageThree']").prev().find(".coral-Select-button-text").text("Please Select a Package");
  });

  $(document).on('selected.select', '.classificationTwo-class', function(e) {
    $("[name='./planTwo']").prev().find(".coral-Select-button-text").text("Please Select a Plan");
    $("[name='./planTwoPackageOne']").prev().find(".coral-Select-button-text").text("Please Select a Package");
    $("[name='./planTwoPackageTwo']").prev().find(".coral-Select-button-text").text("Please Select a Package");
    $("[name='./planTwoPackageThree']").prev().find(".coral-Select-button-text").text("Please Select a Package");
  });

  $(document).on('selected.select', '.classificationThree-class', function(e) {
    $("[name='./planThree']").prev().find(".coral-Select-button-text").text("Please Select a Plan");
    $("[name='./planThreePackageOne']").prev().find(".coral-Select-button-text").text("Please Select a Package");
    $("[name='./planThreePackageTwo']").prev().find(".coral-Select-button-text").text("Please Select a Package");
    $("[name='./planThreePackageThree']").prev().find(".coral-Select-button-text").text("Please Select a Package");
  });



  $(document).on('selected.select', '.planOne-class', function(e) {
    var classificationText = $(".coral-Form-field.classificationOne-class.coral-Select").find("span.coral-Select-button-text").text();
    var classificationValue;
    $("[name='./classificationOne'] > option").each(function() {
      if(classificationText == this.text) {
        classificationValue = this.value;
        return false;
      }
    });
    $.ajax({
      url:'/bin/dynamicPackages?planId='+e.selected+'&classificationOne='+classificationValue,
      type:'GET',
      dataType:'json',
      async: true,
      success: function(resp) {
        var x = $("[name='./planOnePackageOne']").closest(".coral-Select").find('option').remove().end();
        var pack1Element = $("[name='./planOnePackageOne']").next();
        pack1Element.find('li').remove().end();
        var i = 0;
        $.each(resp, function (k, v) {
          if(i ==0) {
            $("[name='./planOnePackageOne']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planOnePackageOne']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack1Element).html(v);
          i++;
        });

        var x = $("[name='./planOnePackageTwo']").closest(".coral-Select").find('option').remove().end();
        var pack2Element = $("[name='./planOnePackageTwo']").next();
        pack2Element.find('li').remove().end();
        var j = 0;
        $.each(resp, function (k, v) {
          if(j ==0) {
            $("[name='./planOnePackageTwo']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planOnePackageTwo']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack2Element).html(v);
          j++;
        });

        var x = $("[name='./planOnePackageThree']").closest(".coral-Select").find('option').remove().end();
        var pack3Element = $("[name='./planOnePackageThree']").next();
        pack3Element.find('li').remove().end();
        var l = 0;
        $.each(resp, function (k, v) {
          if(l ==0) {
            $("[name='./planOnePackageThree']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planOnePackageThree']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack3Element).html(v);
          l++;
        });
      },
      error: function(jqXHR, textStatus, errorThrown) {
      }
    });
  });

  $(document).on('selected.select', '.planTwo-class', function(e) {
    var classificationText = $(".coral-Form-field.classificationTwo-class.coral-Select").find("span.coral-Select-button-text").text();
    var classificationValue;
    $("[name='./classificationTwo'] > option").each(function() {
      if(classificationText == this.text) {
        classificationValue = this.value;
        return false;
      }
    });
    $.ajax({
      url:'/bin/dynamicPackages?planId='+e.selected+'&classificationTwo='+classificationValue,
      type:'GET',
      dataType:'json',
      async: true,
      success: function(resp) {
        var x = $("[name='./planTwoPackageOne']").closest(".coral-Select").find('option').remove().end();
        var pack1Element = $("[name='./planTwoPackageOne']").next();
        pack1Element.find('li').remove().end();
        var m = 0;
        $.each(resp, function (k, v) {
          if(m ==0) {
            $("[name='./planTwoPackageOne']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planTwoPackageOne']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack1Element).html(v);
          m++;
        });

        var x = $("[name='./planTwoPackageTwo']").closest(".coral-Select").find('option').remove().end();
        var pack2Element = $("[name='./planTwoPackageTwo']").next();
        pack2Element.find('li').remove().end();
        var n = 0;
        $.each(resp, function (k, v) {
          if(n ==0) {
            $("[name='./planTwoPackageTwo']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planTwoPackageTwo']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack2Element).html(v);
          n++;
        });

        var x = $("[name='./planTwoPackageThree']").closest(".coral-Select").find('option').remove().end();
        var pack3Element = $("[name='./planTwoPackageThree']").next();
        pack3Element.find('li').remove().end();
        var o = 0;
        $.each(resp, function (k, v) {
          if(o ==0) {
            $("[name='./planTwoPackageThree']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planTwoPackageThree']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack3Element).html(v);
          o++;
        });
      },
      error: function(jqXHR, textStatus, errorThrown) {
      }
    });
  });


  $(document).on('selected.select', '.planThree-class', function(e) {
    var classificationText = $(".coral-Form-field.classificationThree-class.coral-Select").find("span.coral-Select-button-text").text();
    var classificationValue;
    $("[name='./classificationThree'] > option").each(function() {
      if(classificationText == this.text) {
        classificationValue = this.value;
        return false;
      }
    });
    $.ajax({
      url:'/bin/dynamicPackages?planId='+e.selected+'&classificationThree='+classificationValue,
      type:'GET',
      dataType:'json',
      async: true,
      success: function(resp) {
        var x = $("[name='./planThreePackageOne']").closest(".coral-Select").find('option').remove().end();
        var pack1Element = $("[name='./planThreePackageOne']").next();
        pack1Element.find('li').remove().end();
        var p = 0;
        $.each(resp, function (k, v) {
          if(p ==0) {
            $("[name='./planThreePackageOne']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planThreePackageOne']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack1Element).html(v);
          p++;
        });

        var x = $("[name='./planThreePackageTwo']").closest(".coral-Select").find('option').remove().end();
        var pack2Element = $("[name='./planThreePackageTwo']").next();
        pack2Element.find('li').remove().end();
        var q = 0;
        $.each(resp, function (k, v) {
          if(q ==0) {
            $("[name='./planThreePackageTwo']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planThreePackageTwo']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack2Element).html(v);
          q++;
        });

        var x = $("[name='./planThreePackageThree']").closest(".coral-Select").find('option').remove().end();
        var pack3Element = $("[name='./planThreePackageThree']").next();
        pack3Element.find('li').remove().end();
        var r = 0;
        $.each(resp, function (k, v) {
          if(r ==0) {
            $("[name='./planThreePackageThree']").prev().find(".coral-Select-button-text").text(v);
          }
          var pcVar = $("[name='./planThreePackageThree']")[0];
          $("<option>").appendTo(pcVar).val(k).html(v);
          $("<li class='coral-SelectList-item coral-SelectList-item--option' data-value="+k+" aria-selected='false' role='option'>").appendTo(pack3Element).html(v);
          r++;
        });
      },
      error: function(jqXHR, textStatus, errorThrown) {
      }
    });
  });

})($, $(document));

