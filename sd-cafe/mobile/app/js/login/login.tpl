<section id="content" class="m-t-lg wrapper-md animated fadeInUp"> <a class="navbar-brand block login-heading">Babosa Bhagwan</a>
  <div class="container aside-xxl">
    <section class="panel panel-default bg-white m-t-lg">
      <header class="panel-heading text-center">
        <strong>Sign in</strong>
      </header>
      <form name="loginForm" class="panel-body wrapper-lg" ng-submit="userLogin(loginForm.$valid)" novalidate>
        <div class="form-group" ng-class="{'has-error': loginForm.email.$invalid && !loginForm.email.$pristine }">
          <label class="control-label">Email</label>
          <input type="email" name="email" placeholder="Your Email Id" class="form-control input-lg" ng-model="patient.email" required/>
          <p ng-show="loginForm.email.$error.required && !loginForm.email.$pristine" class="help-block">Email ID is Required</p>
          <p ng-show="loginForm.email.$invalid && !loginForm.email.$pristine" class="help-block">Invalid Email ID</p>
        </div>
        <div class="form-group" ng-class="{'has-error' : loginForm.password.$invalid && !loginForm.password.$pristine }">
          <label class="control-label">Password</label>
          <input type="password" id="inputPassword" name="password" placeholder="Your Password" class="form-control input-lg" ng-model="patient.password" required/>
          <p ng-show="loginForm.password.$invalid && !loginForm.password.$pristine" class="help-block">Password can't be empty</p>
        </div>
        <a href="#" class="pull-right m-t-xs">
          <small>Forgot password?</small>
        </a>
        <button type="submit" ng-disabled="loginForm.$invalid" class="btn btn-primary btn-block">Sign in</button>
        <div class="line line-dashed"></div>
        <div class="line line-dashed"></div>
        <p class="text-muted text-center">
          <small>Do not have an account?</small>
        </p> <a class="btn btn-default btn-block">Create an account</a>
      </form>
    </section>
  </div>
</section>
