<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    <header class="main-header">
        <!-- Logo -->
        <a href="#" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini">博客</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg">我的博客</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
           
          </a>
    
          <div class="navbar-custom-menu">
              <ul class="nav navbar-nav">
                 <!-- User Account: style can be found in dropdown.less -->
                 <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                      <img src="/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
                      <span class="hidden-xs">${sessionScope.curr_admin.name}</span>
                    </a>
                    
                  </li>
                  <!-- Messages: style can be found in dropdown.less-->
                  <li class="dropdown messages-menu">
                    <a href="/user/article/list" class="dropdown-toggle">
                      <i class="fa fa-tencent-weibo"></i>
                    </a>
                    
                  </li>
                  <!-- Notifications: style can be found in dropdown.less -->
                  <li class="dropdown notifications-menu">
                    <a href="/admin/notify" class="dropdown-toggle">
                      <i class="fa fa-bell-o"></i>
                      <span class="label label-warning" id="notifySpan"></span>
                    </a>
                
                  </li>
                 
                 
                  <li class="dropdown user user-menu">
                      <a href="javascript:;" class="dropdown-toggle" id="logout">
                        <span class="hidden-xs"><i class="fa fa-sign-in"></i> </span>
                      </a>
                      
                    </li>
                </ul>
              </div>
        </nav>
      </header>
	