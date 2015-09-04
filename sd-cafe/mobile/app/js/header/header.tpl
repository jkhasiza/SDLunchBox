<header class="bg-light lter b-r aside-md hidden-print hidden-xs header-fixed top-zero">
  <div class="navbar-header aside-md">
    <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html"> <i class="fa fa-bars"></i>
    </a> <a href="#!" class="navbar-brand" data-toggle="fullscreen">ADMIN PANEL</a>
    <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user"> <i class="fa fa-cog"></i>
    </a>
  </div>
  <!--   <ul class="nav navbar-nav hidden-xs">
    <li >
      <a ui-sref="home.template-list.my" class="dropdown-toggle dker" data-toggle="dropdown">
        <i class="fa fa-building-o"></i>
        <span class="font-bold">My Templates</span>
      </a>
    </li>

    <li >
      <a ui-sref="home.template-list.public" class="dropdown-toggle dker" data-toggle="dropdown">
        <i class="fa fa-building-o"></i>
        <span class="font-bold">Public Templates</span>
      </a>
    </li>
  </ul> -->
  <ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user">
    <!--  <li class="hidden-xs">
      <a href="#" class="dropdown-toggle dk" data-toggle="dropdown">
        <i class="fa fa-bell"></i>
        <span class="badge badge-sm up bg-danger m-l-n-sm count" style="display: inline-block;">3</span>
      </a>
      <section class="dropdown-menu aside-xl">
        <section class="panel bg-white">
          <header class="panel-heading b-light bg-light">
            <strong>You have
              <span class="count" style="display: inline;">3</span>notifications</strong>
          </header>
          <div class="list-group list-group-alt animated fadeInRight">
            <a href="#" class="media list-group-item" style="display: block;">
              <span class="pull-left thumb-sm text-center">
                <i class="fa fa-envelope-o fa-2x text-success"></i>
              </span>
              <span class="media-body block m-b-none">Sophi sent you a email
                <br>
                <small class="text-muted">1 minutes ago</small>
              </span>
            </a>
            <a href="#" class="media list-group-item">
              <span class="pull-left thumb-sm">
                <img src="images/avatar.jpg" alt="John said" class="img-circle">
              </span>
              <span class="media-body block m-b-none">Use awesome animate.css
                <br>
                <small class="text-muted">10 minutes ago</small>
              </span>
            </a>
            <a href="#" class="media list-group-item">
              <span class="media-body block m-b-none">1.0 initial released
                <br>
                <small class="text-muted">1 hour ago</small>
              </span>
            </a>
          </div>
          <footer class="panel-footer text-sm">
            <a href="#" class="pull-right">
              <i class="fa fa-cog"></i>
            </a>
            <a href="#notes" data-toggle="class:show animated fadeInRight">See all the notifications</a>
          </footer>
        </section>
      </section>
    </li>
    <li class="dropdown hidden-xs">
      <a href="#" class="dropdown-toggle dker" data-toggle="dropdown">
        <i class="fa fa-fw fa-search"></i>
      </a>
      <section class="dropdown-menu aside-xl animated fadeInUp">
        <section class="panel bg-white">
          <form role="search">
            <div class="form-group wrapper m-b-none">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Search">
                <span class="input-group-btn">
                  <button type="submit" class="btn btn-info btn-icon">
                    <i class="fa fa-search"></i>
                  </button>
                </span>
              </div>
            </div>
          </form>
        </section>
      </section>
    </li> -->
    <li class="dropdown cursor-pointer" ng-controller="HeaderCtrl">
      <a class="dropdown-toggle" data-toggle="dropdown">
        <span class="thumb-sm avatar pull-left">
          <img src="img/avatar.jpg">
        </span> {{user.firstName}} {{user.lastName}} <b class="caret"></b>
      </a>
      <ul class="dropdown-menu animated fadeInRight">
        <span class="arrow top"></span>
        <!--  <li> <a href="#">Settings</a>
        </li> -->
        <li> <a ng-click="myprofile()" class="btn btn-success btn-block"><i class="fa fa-user"></i> Profile</a>
        </li>
        <!--  <li>
          <a href="#">
            <span class="badge bg-danger pull-right">3</span>Notifications</a>
        </li> -->
        <!-- <li> <a href="docs.html">Help</a>
        </li> -->
        <li class="divider"></li>
        <li>
          <button class="btn btn-danger btn-block" ng-click="logout()"><i class="fa fa-sign-out"></i> Logout</button>
        </li>
      </ul>
    </li>
  </ul>
</header>
