<%-- 
    Document   : form
    Created on : 11 déc. 2023, 22:52:20
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<main role="main" class="main-content">

<div class="card shadow mb-4">
                  <div class="card-header">
                    <strong class="card-title">Horizontal form</strong>
                  </div>
                  <div class="card-body">
                    <form>
                      <div class="form-group row">
                        <label for="inputEmail3" class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                          <input type="email" class="form-control" id="email" placeholder="Email">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="inputPassword3" class="col-sm-3 col-form-label">Date</label>
                        <div class="col-sm-9">
                          <input type="date" class="form-control" id="date" name="date" >
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="inputPassword3" class="col-sm-3 col-form-label">Password</label>
                        <div class="col-sm-9">
                          <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
                        </div>
                      </div>
                      <div class="form-group row">
                        <label for="disabledInput" class="col-sm-3 col-form-label">Disabled</label>
                        <div class="col-sm-9">
                          <input class="form-control" id="disabledInput" type="text" placeholder="Disabled input here..." disabled>
                        </div>
                      </div>
                      <fieldset class="form-group">
                        <div class="row">
                          <label class="col-form-label col-sm-3 pt-0">Radios</label>
                          <div class="col-sm-9">
                            <div class="form-check">
                              <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="option1" checked>
                              <label class="form-check-label" for="gridRadios1"> First radio </label>
                            </div>
                            <div class="form-check">
                              <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="option2">
                              <label class="form-check-label" for="gridRadios2"> Second radio </label>
                            </div>
                            <div class="form-check disabled">
                              <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="option3" disabled>
                              <label class="form-check-label" for="gridRadios3"> Third disabled radio </label>
                            </div>
                          </div>
                        </div>
                      </fieldset>
                      <div class="form-group row">
                        <div class="col-sm-3">Checkbox</div>
                        <div class="col-sm-9">
                          <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="gridCheck1">
                            <label class="form-check-label" for="gridCheck1"> Checkbox </label>
                          </div>
                        </div>
                      </div>
                      <div class="form-group row">
                        <label class="col-sm-3" for="exampleFormControlTextarea1">Textarea</label>
                        <div class="col-sm-9">
                          <textarea class="form-control" id="exampleFormControlTextarea1" rows="2"></textarea>
                        </div>
                      </div>
                      <div class="form-group mb-2">
                        <button type="submit" class="btn btn-primary">Sign in</button>
                      </div>
                    </form>
                  </div>
                </div>
      </main> <!-- main -->
<%@ include file="footer.jsp" %>