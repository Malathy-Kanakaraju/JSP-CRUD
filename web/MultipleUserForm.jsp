<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add multiple users</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />         
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />         
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js" ></script>
        <script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>     
        <style>
            .centre {
                margin: auto;
                width: 90%;
            }
            .fieldGroupCopy {
                visibility: hidden;
            }
            .fieldGroup {
            }
            .input-set{
                padding: 5px;
            }
        </style>
    </head>
    <body>
    <center>
        <h1>User Form</h1>
        <h2>
            <a href="new">Add User</a>&nbsp;&nbsp;&nbsp;
            <a href="list">Show all users</a>
        </h2>
    </center>
    <div class="centre">
        <button class="btn btn-success" id="add-input-btn">Add row</button>
        <form class="form-horizontal" action="insertList" method="post" name="add-multiple-user">
            <div class="form-group fieldGroup">
                <div class="input-set row">
                    <div class="col-sm-3">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                            </div>
                            <input type="text" class="form-control" name="firstName" id="f-name" placeholder="First Name">
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                            </div>
                            <input type="text" class="form-control" name="lastName" id="l-name" placeholder="Last Name">
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="basic-addon1"><i class="fa fa-home"></i></span>
                            </div>
                            <input type="text" class="form-control" name="address" id="address" placeholder="Address">
                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Save User List</button>
        </form>
        <div class="form-group fieldGroupCopy" >
            <div class="input-set row">
                <div class="col-sm-3">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" name="firstName" id="f-name" placeholder="First Name">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" name="lastName" id="l-name" placeholder="Last Name">
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1"><i class="fa fa-home"></i></span>
                        </div>
                        <input type="text" class="form-control" name="address" id="address" placeholder="Address">
                    </div>
                </div>
                <div class="col-sm-3">
                    <button class="btn btn-danger remove-input-set">X</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            var formCopy = $(".fieldGroupCopy").html();
            $("#add-input-btn").on("click", function () {
                var newInputSet = '<div class="form-group fieldGroup">' + formCopy + '</div>';
                $("body").find(".fieldGroup:last").after(newInputSet);
            });

            $("body").on("click", ".remove-input-set", function () {
                $(this).parents(".fieldGroup").remove();
            })
        });
    </script>
</body>
</html>
