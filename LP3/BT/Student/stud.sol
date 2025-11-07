// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

contract StudentData {
    // Structure to store student details
    struct Student {
        string name;
        uint rollNo;
        string course;
        uint marks;
    }

    // Array to store multiple students
    Student[] public students;

    // Variable to store which function (fallback/receive) was triggered
    string public lastFunctionCalled;

    // Function to add student data
    function addStudent(
        string memory _name,
        uint _rollNo,
        string memory _course,
        uint _marks
    ) public {
        students.push(Student(_name, _rollNo, _course, _marks));
    }

    // Function to get a student's data by index
    function getStudent(uint index)
        public
        view
        returns (string memory name, uint rollNo, string memory course, uint marks)
    {
        require(students.length > 0, "No student data added yet");
        require(index < students.length, "Invalid student index");

        Student storage s = students[index];
        return (s.name, s.rollNo, s.course, s.marks);
    }

    // Function to get total number of students
    function totalStudents() public view returns (uint) {
        return students.length;
    }

    // --- Fallback Function ---
    fallback() external payable {
        lastFunctionCalled = "Fallback called";
    }

    // --- Receive Function ---
    receive() external payable {
        lastFunctionCalled = "Receive called";
    }
}
