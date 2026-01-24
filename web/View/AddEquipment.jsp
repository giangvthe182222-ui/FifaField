<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Equipment</title>
    <style>
        * {
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            margin: 0;
            height: 100vh;
            background: linear-gradient(135deg, #2ecc71, #27ae60);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background: #ffffff;
            width: 420px;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.15);
        }

        .container h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #27ae60;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
            color: #333;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        .form-group textarea {
            resize: none;
            height: 80px;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #27ae60;
        }

        .btn-submit {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 6px;
            background: #27ae60;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }

        .btn-submit:hover {
            background: #219150;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Add Equipment</h2>

    <form action="addEquipment" method="post">
        <div class="form-group">
            <label>Equipment Name</label>
            <input type="text" name="equipmentName" required>
        </div>

        <div class="form-group">
            <label>Equipment Type</label>
            <select name="equipmentType">
                <option value="Ball">Ball</option>
                <option value="Net">Net</option>
                <option value="Shoes">Shoes</option>
                <option value="Jersey">Jersey</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <div class="form-group">
            <label>Quantity</label>
            <input type="number" name="quantity" min="1" required>
        </div>

        <div class="form-group">
            <label>Condition</label>
            <select name="condition">
                <option value="New">New</option>
                <option value="Good">Good</option>
                <option value="Damaged">Damaged</option>
            </select>
        </div>

        <div class="form-group">
            <label>Description</label>
            <textarea name="description"></textarea>
        </div>

        <button type="submit" class="btn-submit">Add Equipment</button>
    </form>
</div>

</body>
</html>
