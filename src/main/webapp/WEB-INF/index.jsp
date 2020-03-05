<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Edit Remove HTML Table Row</title>
    <meta charset="windows-1252">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
.container {
	overflow: hidden
}

.tab {
	float: left;
}

.tab-2 {
	margin-left: 50px
}

.tab-2 input {
	display: block;
	margin-bottom: 10px
}

tr {
	transition: all .25s ease-in-out
}

tr:hover {
	background-color: #EEE;
	cursor: pointer
}

.modalWindow {
	display: none;
	position: fixed;
	z-index: 1;
	margin: % auto;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgb(0, 0, 0, 0.6);
}

.tab-2 {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solidd #888;
	width: 80%;
}

.close {
	color: grey;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

</style>

</head>
<body>

<div class="container">
    <div class="tab tab-1">
        <table id="table" border="1">
            <tr>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Age</th>
                <th>Birth day</th>
                <th>Faculty</th>
            </tr>
        </table>
    </div>

    <div id="modal" class="modalWindow">
        <div class="tab tab-2">
            <span onclick="closeModalWindow()" class="close">&times;</span>
            FirstName :<input type="text" name="fname" id="fname">
            LastName :<input type="text" name="lname" id="lname"> Age :<input
                type="number" name="age" id="age"> Birth day :<input
                type="date" name="bday" id="bday"> Faculty :<input
                type="text" name="faculty" id="faculty">

            <button onclick="addHtmlTableRow();">Add Student</button>
            <button onclick="editHtmlTbleSelectedRow();">Edit</button>
            <button onclick="removeSelectedRow();">Remove</button>
        </div>
    </div>
    <button onclick="openModalWindow();" id="modalBtn">Add</button>
    <button>Export table</button>
</div>

<script>
		var rIndex, table = document.getElementById("table");
		var modal = document.getElementById("modal");
		var btn = document.getElementById("modalBtn")
		var span = document.getElementByClassName("close")[0];

		//open modal window
		function openModalWindow() {
			modal.style.display = "block";
		}
		//close modal window
		function closeModalWindow() {
			modal.style.display = "none";
		}

		// check the empty input
		function checkEmptyInput() {
			var isEmpty = false, fname = document.getElementById("fname").value, lname = document
					.getElementById("lname").value, age = document
					.getElementById("age").value, bday = document
					.getElementById("bday").value, faculty = document
					.getElementById("faculty").value;

			if (fname === "") {
				alert("First Name Connot Be Empty");
				isEmpty = true;
			} else if (lname === "") {
				alert("Last Name Connot Be Empty");
				isEmpty = true;
			} else if (age === "") {
				alert("Age Connot Be Empty");
				isEmpty = true;
			} else if (bday == "") {
				alert("Birth Day Connot Be Empty");
				isEmpty = true;
			} else if (faculty == "") {
				alert("Faculty Connot Be Empty");
				isEmpty = true;
			}
			return isEmpty;
		}

		// add Row
		function addHtmlTableRow() {
			// get the table by id
			// create a new row and cells
			// get value from input text
			// set the values into row cell's
			if (!checkEmptyInput()) {

				var newRow = table.insertRow(table.length), cell1 = newRow
						.insertCell(0), cell2 = newRow.insertCell(1), cell3 = newRow
						.insertCell(2), cell4 = newRow.insertCell(3), cell5 = newRow
						.insertCell(4), fname = document
						.getElementById("fname").value, lname = document
						.getElementById("lname").value, age = document
						.getElementById("age").value, bday = document
						.getElementById("bday").value, faculty = document
						.getElementById("faculty").value;

				cell1.innerHTML = fname;
				cell2.innerHTML = lname;
				cell3.innerHTML = age;
				cell4.innerHTML = bday;
				cell5.innerHTML = faculty;
				// call the function to set the event to the new row
				selectedRowToInput();
			}
		}

		// display selected row data into input text
		function selectedRowToInput() {

			for (var i = 1; i < table.rows.length; i++) {
				table.rows[i].onclick = function() {
					// get the seected row index
					rIndex = this.rowIndex;
					document.getElementById("fname").value = this.cells[0].innerHTML;
					document.getElementById("lname").value = this.cells[1].innerHTML;
					document.getElementById("age").value = this.cells[2].innerHTML;
					document.getElementById("bday").value = this.cells[3].innerHTML;
					document.getElementById("faculty").value = this.cells[4].innerHTML;
					modal.style.display = "block";
				};
			}
		}
		selectedRowToInput();

		function editHtmlTbleSelectedRow() {
			var fname = document.getElementById("fname").value, lname = document
					.getElementById("lname").value, age = document
					.getElementById("age").value, bday = document
					.getElementById("bday").value, faculty = document
					.getElementById("faculty").value;
			if (!checkEmptyInput()) {
				table.rows[rIndex].cells[0].innerHTML = fname;
				table.rows[rIndex].cells[1].innerHTML = lname;
				table.rows[rIndex].cells[2].innerHTML = age;
				table.rows[rIndex].cells[3].innerHTML = bday;
				table.rows[rIndex].cells[4].innerHTML = faculty;
			}
		}

		function removeSelectedRow() {
			table.deleteRow(rIndex);
			// clear input text
			document.getElementById("fname").value = "";
			document.getElementById("lname").value = "";
			document.getElementById("age").value = "";
			document.getElementById("bday").value = "";
			document.getElementById("faculty").value = "";
		}
	</script>

</body>
</html>