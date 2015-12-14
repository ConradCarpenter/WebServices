require(["dijit/form/Button", "dijit/form/ValidationTextBox",
    "dojo/dom", "dojo/dom-construct",
    "dojo/dom-class", "dojo/domReady!"
  ],
  function(Button, ValidationTextBox, dom, con, cl) {

    var in1 = con.create("input", {
      Id: "uid"
    }, "start");
    var in2 = con.create("input", {
      Id: "pwd"
    }, "start");
    var btn1 = con.create("button", {
      Id: "btn1"
    }, "start");
    var btn2 = con.create("button", {
      Id: "btn2"
    }, "start");
    var res = con.create("div", {
      Id: "resultDiv"
    }, "start");
    var div = con.create("div", {
      Id: "gfx"
    }, "start");

    var uid_t = new ValidationTextBox({
      name: "user",
      type: "text",
      placeHolder: "UserName"
    }, "uid");

    var pwd_t = new ValidationTextBox({
      name: "password",
      type: "password",
      placeHolder: "Password"
    }, "pwd");

    var button1 = new Button({
      showLabel: true,
      label: "Sign In", // analogous to title when showLabel is false
      onClick: function(evt) {
        u = dom.byId("uid").value;
        p = dom.byId("pwd").value;
        if (p == "" || u == "") {
          dom.byId("resultDiv").innerHTML = "Password or Username empty";
        }
        // prevent the page from navigating after submit
        evt.stopPropagation();
        evt.preventDefault();
        var rest = "http://localhost:8084/Project/rest/";
        post_json(rest, u, p); //call to dojo AJAX REST service
      }
    }, "btn1").startup();
    var button2 = new Button({
      showLabel: true,
      label: "Sign Up", // analogous to title when showLabel is false
      onClick: function(evt) {
        var in1 = con.create("input", {
          Id: "pwd2"
        }, "start");
        var res = con.create("span", {
          Id: "chkdiv1"
        }, "start");
        var chk1 = con.create("CheckBox", {
          Id: "chk1"
        }, "start");
        var res = con.create("span", {
          Id: "chkdiv2"
        }, "start");
        var chk2 = con.create("CheckBox", {
          Id: "chk2"
        }, "start");
        var res = con.create("span", {
          Id: "chkdiv3"
        }, "start");
        var chk3 = con.create("CheckBox", {
          Id: "chk3"
        }, "start");
         var res = con.create("span", {
          Id: "chkdiv4"
        }, "start");
        var chk3 = con.create("CheckBox", {
          Id: "chk4"
        }, "start");
         var res = con.create("span", {
          Id: "chkdiv5"
        }, "start");
        var chk3 = con.create("CheckBox", {
          Id: "chk5"
        }, "start");
        var btn1 = con.create("button", {
     		 Id: "createaccountbutton"
   		 }, "start");
        
        var pwd_t = new ValidationTextBox({
          name: "password2",
          type: "password2",
          placeHolder: "Confirm Password"
        }, "pwd2");
        require(["dijit/form/CheckBox", "dojo/domReady!"], function(CheckBox) {
          var checkBox = new CheckBox({
            name: "checkBox",
						value: "sports",
            checked: false,
          }, "chk1").startup();
           dom.byId("chkdiv1").innerHTML = "Sports";
          var checkBox = new CheckBox({
            name: "checkBox",
            value: "agreed",
            checked: false,
          }, "chk2").startup();
           dom.byId("chkdiv2").innerHTML = "<br> Gadgets";
          var checkBox = new CheckBox({
            name: "checkBox",
            value: "agreed",
            checked: false,
          }, "chk3").startup();
           dom.byId("chkdiv3").innerHTML = "<br> Gaming";
            var checkBox = new CheckBox({
            name: "checkBox",
            value: "agreed",
            checked: false,
          }, "chk4").startup();
           dom.byId("chkdiv4").innerHTML = "<br> Music";
            var checkBox = new CheckBox({
            name: "checkBox",
            value: "agreed",
            checked: false,
          }, "chk5").startup();
           dom.byId("chkdiv5").innerHTML = "<br> Food";
             var button3 = new Button({
      showLabel: true,
      label: "Create Account", // analogous to title when showLabel is false
      onClick: function(evt) {
   
       
        u = dom.byId("uid").value;
        p2 = dom.byId("pwd2").value;
        p = dom.byId("pwd").value;
        if (p == "" || p2 == "" || u == "") {
          dom.byId("resultDiv").innerHTML = "Password or Username empty";
        }
        else if (p == p2){
        	empty();
        }
        else{
        dom.byId("resultDiv").innerHTML = "Passwords are not equal";
        }
      }
    }, "createaccountbutton").startup();
        });
      }
    }, "btn2").startup();

  });

//AMD dojo get form strings with pwd blocker?

//AMD dojo AJAX
function get_json(url) {
  require(["dojo/json", "dojo/dom", "dojo/on", "dojo/request", "dojo/domReady!"],
    function(JSON, dom, on, request) {
      // Request the text file
      request.get(url, {
        headers: {
          'X-Requested-With': null,
          'Content-Type': 'application/json'
        }
      }).then(
        function(response) {
          // Display the text file content
          dom.byId("resultDiv").innerHTML = "<pre>" + response + "</pre>";
        },
        function(error) {
          // Display the error returned
          dom.byId("resultDiv").innerHTML = "<div class=\"error\">" + error + "<div>";
        }
      );
    });
}
function empty(){
   require(["dojo/dom-construct"], function(domConstruct){
  // Empty node's children byId:
  domConstruct.empty("start");
});
}

//AMD dojo AJAX
function post_json(url, uid, pwd) {
  require(["dojo/json", "dojo/dom", "dojo/on", "dojo/request", "dojo/domReady!"],
    function(JSON, dom, on, request) {
      var data = {
        "uid": uid,
        "name": pwd
      }; //access the hash
      //console.log(data);
      // Request with some data input
      request.post(url + "data.table", {
        headers: {
          'X-Requested-With': null,
          'Content-Type': 'application/json'
        },
        data: JSON.stringify(data),
        timeout: 3000
      }).then(
        function(response) {
          // Display the text file content
          var obj = JSON.parse(response);
          console.log(obj);
          //obj.uid_pwd_hash
          dom.byId("resultDiv").innerHTML = "<pre>" + obj + "</pre>";
        },
        function(error) {
          // Display the error returned
          dom.byId("resultDiv").innerHTML = "<div class=\"error\">" + error + "<div>";
        }
      );
    });
}
