<section id="content" class="m-t-lg wrapper-md animated fadeInUp"> <a class="navbar-brand block login-heading">Patient Health Portal</a>
  <div class="container aside-xxl">
    <section class="panel panel-default bg-white m-t-lg">
      <header class="panel-heading text-center">
        <strong>Activate Your Account</strong>
      </header>
      <form class="panel-body wrapper-lg" ng-submit="activateAccount()" name="doctorForm">
        <div class="form-group">
          <label class="control-label">Email</label>
          <input type="text" class="form-control input-lg" ng-model="patient.contactInformation.email" required ng-disabled="true">
        </div>
        <div class="form-group">
          <label class="control-label">First Name</label>
          <input type="text" placeholder="Your First Name" class="form-control input-lg" ng-model="patient.firstName" required>
        </div>
        <div class="form-group">
          <label class="control-label">Last Name</label>
          <input type="text" placeholder="Your Last Name" class="form-control input-lg" ng-model="patient.lastName" required>
        </div>
        <div class="form-group" ng-class="{'has-error' : doctorForm.pwd1.$invalid && !doctorForm.pwd1.$pristine }">
          <label class="control-label">Choose Password</label>
          <input type="password" name="pwd1" class="form-control" id="pwd1" placeholder="Choose Password" ng-model="patient.password" ng-minlength="4" required>
          <p ng-show="doctorForm.pwd1.$error.required && !doctorForm.pwd1.$pristine " class="help-block">Password required!</p>
          <span class="help-block" ng-show="!doctorForm.pwd1.$pristine && doctorForm.pwd1.$error.minlength">Password too short</span>
        </div>
        <div class="form-group" ng-class="{'has-error' : doctorForm.confirmPassword.$error.pwmatch}">
          <label class="control-label">Confirm Password</label>
          <input type="password" name="confirmPassword" class="form-control" id="pwd2" placeholder="Confirm Password" ng-model="confirmPassword" required pw-check="pwd1" />
          <div class="msg-block" ng-show="doctorForm.$error">
            <span class="help-block" ng-show="doctorForm.confirmPassword.$error.pwmatch">Passwords don't match.</span>
          </div>
        </div>
        <!--  <div class="form-group" ng-class="{'has-error' : doctorForm.password.$invalid && !doctorForm.password.$pristine }">
          <label class="control-label">Password</label>
          <input type="password" name="password " placeholder="Choose A Password" class="form-control input-lg" data-ng-model="patient.password" required>
          <div ng-show="doctorForm.password.$error.required">Field required</div>
        </div>
        <div class="form-group" ng-class="{'has-error' : doctorForm.confirmPassword.$invalid && !doctorForm.password.$pristine }">
          <label class="control-label">confirm password</label>
          <input type="password" name="confirmPassword" placeholder="confirm password" class="form-control input-lg" ng-model="confirmPassword" pw-check="password">
          <div class="msg-block" ng-show="doctorForm.$error">
            <span class="help-block" ng-show="doctorForm.confirmPassword.$error.pwmatch">Passwords don't match.</span>
          </div>
        </div> -->
        <div class="form-group">
          <label class="control-label">E-mail Activation Code</label>
          <input type="text" placeholder="Enter Your Email Activation Code " class="form-control input-lg" ng-model="patient.activationCode">
        </div>
        <div class="form-group">
          <label class="control-label">Mobile Number (Ext)</label>
          <div class="row">
            <div class="col-md-10">
              <input type="text" placeholder="Enter Your Mobile Number " class="form-control input-lg" ng-model="patient.contactInformation.mobileNumber">
            </div>
            <div class="col-md-2">
              <button class="btn btn-warning btn-height" tooltip-placement="top" tooltip="Send OTP Password" ng-click="sendCode()"><i class="fa fa-mobile icon-font-mobile bounce animated"></i>
              </button>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label">Mobile Activation Code</label>
          <input type="text" placeholder="Enter Your Sms Activation Code " class="form-control input-lg" ng-model="patient.smsCode">
        </div>
        <button type="submit" class="btn btn-danger btn-block">Activate Now</button>
      </form>
    </section>
  </div>
</section>
