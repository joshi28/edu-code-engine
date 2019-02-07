
		// Load the SDK asynchronously
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));

		// This is called with the results from from FB.getLoginStatus().
		window.fbAsyncInit = function() {
			FB.init({
				appId : '627217327730448',
				cookie : true, // enable cookies to allow the server to access 
				// the session
				xfbml : true, // parse social plugins on this page
				version : 'v3.2' // The Graph API version to use for the call
			});

			// Now that we've initialized the JavaScript SDK, we call 
			// FB.getLoginStatus().  This function gets the state of the
			// person visiting this page and can return one of three states to
			// the callback you provide.  They can be:
			//
			// 1. Logged into your app ('connected')
			// 2. Logged into Facebook, but not your app ('not_authorized')
			// 3. Not logged into Facebook and can't tell if they are logged into
			//    your app or not.
			//
			// These three cases are handled in the callback function.

			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});

		};

		function statusChangeCallback(response) {
			console.log('statusChangeCallback');
			console.log(response);
			// The response object is returned with a status field that lets the
			// app know the current login status of the person.
			// Full docs on the response object can be found in the documentation
			// for FB.getLoginStatus().
			if (response.status === 'connected') {
				// Logged into your app and Facebook.
				//call controller
				//testAPI();
				getUserDataFromFB();
			}else if (response.status === 'authorization_expired') {
			    // The user has signed into your application with
			    // Facebook Login but must go through the login flow
			    // again to renew data authorization. You might remind
			    // the user they've used Facebook, or hide other options
			    // to avoid duplicate account creation, but you should
			    // collect a user gesture (e.g. click/touch) to launch the
			    // login dialog so popup blocking is not triggered.
			  } else if (response.status === 'not_authorized') {
			    // The user hasn't authorized your application.  They
			    // must click the Login button, or you must call FB.login
			    // in response to a user gesture, to launch a login dialog.
			  }
			  else {
				// The person is not logged into your app or we are unable to tell.
				document.getElementById('status').innerHTML = 'Please log '
						+ 'into this app.';
			}
		}

		
		
		function fbLogin() {
		    FB.login(function (response) {
		        if (response.authResponse) {
		            // Get and display the user profile data
		            getUserDataFromFB();
		        } else {
		            document.getElementById('status').innerHTML = 'User cancelled login or did not fully authorize.';
		        }
		    }, {scope: 'email'});
		}

		function fbLogout() {
		    FB.logout(function() {
		        document.getElementById('fblogin').setAttribute("onclick","fbLogin()");
		        document.getElementById('fblogin').innerHTML = '<i class="fa fa-facebook fa-fw"></i>'+'Login with Facebook';
		        document.getElementById('userData').innerHTML = '';
		        document.getElementById('status').innerHTML = 'You have successfully logout from Facebook.';
		    });
		}
		// Here we run a very simple test of the Graph API after login is
		// successful.  See statusChangeCallback() for when this call is made.
		function getUserDataFromFB() {
			FB
					.api(
							'/me',
							{
								locale : 'en_US',
								fields : 'id,first_name,last_name,email,link,gender,locale,picture'
							},
							function(response) {
								document.getElementById('fblogin').setAttribute("onclick","fbLogout()");
						        document.getElementById('fblogin').innerHTML = '<i class="fa fa-facebook fa-fw"></i>'+'Logout from Facebook';
								document.getElementById('status').innerHTML = 'Thanks for logging in, '
										+ response.first_name + '!';
								document.getElementById('userData').innerHTML = '<p><b>FB ID:</b> '
										+ response.id
										+ '</p><p><b>Name:</b> '
										+ response.first_name
										+ ' '
										+ response.last_name
										+ '</p><p><b>Email:</b> '
										+ response.email
										+ '</p><p><b>Gender:</b> '
										+ response.gender
										+ '</p><p><b>Locale:</b> '
										+ response.locale
										+ '</p><p><b>Picture:</b> <img src="'+response.picture.data.url+'"/></p><p><b>FB Profile:</b> <a target="_blank" href="'+response.link+'">click to view profile</a></p>';
								saveUserData(response);

							});
		}
		function saveUserData(responseData) {
			$.post('LoginController', {
				oauth_provider : 'facebook',
				userData : JSON.stringify(responseData)
			}, function(data) {
				return true;
			});
		}
		function testAPI() {
			console.log('Welcome!  Fetching your information.... ');
			FB
					.api(
							'/me',
							function(response) {
								console.log('Successful login for: '
										+ response.name);
								document.getElementById('status').innerHTML = 'Thanks for logging in, '
										+ response.name + '!';
							});
		}