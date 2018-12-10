let count=0;
let persons=[];
// document.getElementsByName('nonVisible')[0].style.display="none";
console.log('aku');
function addButtonClick() {
  const valid = checkForm();
  if (valid) {
    getRow();
  }
}

function checkForm(){
  let canContinue=0;

  let user=document.getElementById('fname').value;
  console.log('user.value', user)
  console.log(document.getElementById('fname'));
  if (user.length<=1){
    document.getElementById('checkName').innerHTML= '* The field must include at least 1 character';
  }
  else {
    document.getElementById('checkName').innerHTML= '';
    canContinue++;
  }

  let user2=document.getElementById('lname').value;
  if (user2.length<=1){
    document.getElementById('checkLName').innerHTML= '* The field must include at least 1 character';
  }
  else {
    document.getElementById('checkLName').innerHTML= '';
    canContinue++;
  }

  let age1=document.getElementById('age').value;
  if (age1<=0){
    document.getElementById('checkAge').innerHTML= '* The number must be positive';
  }
  else {
    document.getElementById('checkAge').innerHTML= '';
    canContinue++;
  }

  let gender2=document.getElementsByName('gender')[0].checked;
  let gender3=document.getElementsByName('gender')[1].checked;
  if (gender2===false && gender3===false){
    document.getElementById('checkGender').innerHTML= '* You must choose the gender';
  }
  else {
    document.getElementById('checkGender').innerHTML= '';
    canContinue++;
  }

  return canContinue===4;

}

function getRow() {

  count++;
  let firstname=document.getElementById('fname').value;
  let lastname=document.getElementById('lname').value;
  let age=document.getElementById('age').value;
  let gender1;
  // let gender1=document.getElementsByName('gender')[0].value;
  if (document.getElementsByName('gender')[0].checked)
    gender1="M";
  if (document.getElementsByName('gender')[1].checked)
    gender1="F";


  let person = {
    id:count,
    fname:firstname,
    lname:lastname,
     age:age,
     gender:gender1
     // class:deleteBtn //dokoncit v person
  };
  persons.push(person);

  console.log('name: '+ person.fname);
  repaintTable(person);
  console.log('name: '+ persons);


}

const deletePerson=(id)=>{
console.log(id);
let index=persons.findIndex(x => x.id === id);
console.log('I am going to delete: '+persons[index].lastname+' '+index);
persons.splice(index, 1);
$('#autoContent').empty();

persons.map(x=>{
  repaintTable(x);
  console.log(x);
});
// console.log(persons);
}

const repaintTable = (person) => {

    let column0=$('<td>').text(person.id);
    let column1=$('<td>').text(person.fname);
    let column2=$('<td>').text(person.lname);
    let column3=$('<td>').text(person.age);
    let column4=$('<td>');
    if (person.gender=="M"){
      column4.text("Male");

    }
    else if (person.gender=="F")
      column4.text("Female");
    else
      column4.text("Not defined");

    let column5=$('<td>');
    let text='deletePerson('+person.id+')';
    let image=$('<img>').attr('src','x.png').attr('onclick', text).attr('onmouseover', 'changeImg(this)');



    column5.append(image);

    let row=$('<tr>');
    row.append(column0);
    row.append(column1);
    row.append(column2);
    row.append(column3);
    row.append(column4);
    row.append(column5);

    if (person.gender=="M"){
      row.attr('class', 'muz');
    }

    if (person.gender=="F"){
      row.attr('class', 'zena');
    }

    $('#tblResult').append(row);

    document.getElementById('fname').value = '';
    //to iste ako:
    // $('fname').val('');
    document.getElementById('lname').value = '';
    document.getElementById('age').value = '';
    if (document.getElementsByName('gender')[0].checked)
      document.getElementsByName('gender')[0].checked=false;
    if (document.getElementsByName('gender')[1].checked)
      document.getElementsByName('gender')[1].checked=false;

}

    var savebtn = function(){
      // console.log('skuska');
      if (typeof(Storage) !== "undefined") {
        let text = JSON.stringify(persons);
          localStorage.setItem("persons", text);
          localStorage.setItem("counter", count);
      }
    }


    var loadbtn = function(){
      console.log('skuska');
      if (typeof(Storage) !== "undefined") {
          let text=localStorage.getItem("persons");
          count=localStorage.getItem("counter");
          persons= JSON.parse(text);
          $('#autoContent').empty();
          persons.map(x=>{
            repaintTable(x);
          });
      }
    }

    function changeImg(){
      // $( this ).attr('src', 'differentx.png');

      $("img").hover(function() {
        $(this).attr('src', 'differentx.png');
      }, function() {
        $(this).attr('src', 'x.png');
      });
      console.log("huraa");
    }

    function sortByAge(){
      persons.sort( (a,b)=> {
        let c1=parseInt(a.age);
        let c2=parseInt(b.age);
        if (c1 < c2) {return -1;}
        if (c1 > c2) {return 1;}
        return 0;
    });
      $('#autoContent').empty();
      persons.map(x=>{
        repaintTable(x);
      });
    }

    function sortByName(){
      persons.sort( (a,b)=> {
        let c1=a.lname;
        let c2=b.lname;
        let d1=a.fname;
        let d2=b.fname;
        if (c1 < c2) {return -1;}
        if (c1 > c2) {return 1;}
        if (d1 < d2) {return -1;}
        if (d1 > d2) {return 1;}
        return 0;
      });


      $('#autoContent').empty();
      persons.map(x=>{
        repaintTable(x);
      });


    }
        //
        //
        // $( "img" ).hover(function() {
        //   // $( this ).attr('src', 'differentx.png');
        //   console.log("huraa");
        // }, function() {
        //   // $( this ).attr('src', 'x.png');
        //   console.log("huraaaaaaaa");
        // });
