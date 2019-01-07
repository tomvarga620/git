function selectContinent() {
  let req = "http://itsovy.sk:1200/continents";
  let xhttp1 = new XMLHttpRequest();
  xhttp1.onreadystatechange = function () {
    if (this.readyState == 4 && this.status ==200){
      let obj = JSON.parse(this.responseText);
      for (let i=0; i < req.length; i++){
        let x = document.createElement("OPTION");
        x.setAttribute("value", obj[i].continent);
        // x.setAttribute("onclick", getCountry(x)); //stale pise undefined, skusit dat do jednej funkcie vsetko
        x.setAttribute("id", obj[i].continent);
        let t = document.createTextNode(obj[i].continent);
        x.appendChild(t);
        document.getElementById("continents").appendChild(x);
      }

    }
  };
  xhttp1.open("GET", req, true);
  xhttp1.send();

  // getCountry();

}

selectContinent();

function getCountry() {
  let req2 = "http://itsovy.sk:1200/countries?continent=";
  let continent1= document.getElementById('continents').value;
  continent1 = encodeURI(continent1);
  req2+= continent1;
  console.log(continent1);
  console.log(req2);
  let xhttp2 = new XMLHttpRequest();
  xhttp2.onreadystatechange = function () {
    if (this.readyState == 4 && this.status ==200){
      let obj1 = JSON.parse(this.responseText);
      $("#countries").empty();
      for (let i=0; i < req2.length; i++){
        let x = document.createElement("OPTION");
        x.setAttribute("value", obj1[i].name);
        x.setAttribute("id", obj1[i].code);
        let t = document.createTextNode(obj1[i].name);
        x.appendChild(t);
        document.getElementById("countries").appendChild(x);
      }

    }
  };
  xhttp2.open("GET", req2, true);
  xhttp2.send();
}

function getCity() {
  let req3 = "http://itsovy.sk:1200/cities?country=";
  let country3 = document.getElementById('countries').value;
  country3 = encodeURI(country3);
  req3 += country3;
  console.log(country3);
  let xhttp3 = new XMLHttpRequest();
  xhttp3.onreadystatechange = function () {
    if (this.readyState == 4 && this.status ==200){
      let obj3 = JSON.parse(this.responseText);
      $("#cities").empty();
      for (let i=0; i < req3.length; i++){
        let x = document.createElement("OPTION");
        x.setAttribute("value", obj3[i].name);
        let t = document.createTextNode(obj3[i].name);
        x.appendChild(t);
        document.getElementById("cities").appendChild(x);
      }
    }
  };
  xhttp3.open("GET", req3, true);
  xhttp3.send();
}

function getWeather() {
  let city1 = document.getElementById('cities').value;
  let city=city1;
  city = encodeURI(city);
  // let country1 = document.getElementById('countries').value;
  let country1 = countries.options[countries.selectedIndex].id;
  let country=country1;
  country= encodeURI(country);
  console.log('city: '+city+ ' country: '+country);
  let appid="850bf2f1030e0ac754a4acb6582d1d97";
  let req4="http://api.openweathermap.org/data/2.5/forecast?";
  req4=req4+"q="+city;
  req4=req4+","+country;
  req4=req4+"&mode=HTML";
  req4=req4+"&appid="+appid;
  console.log(req4);
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status ==200){
      document.getElementById("wrong").innerHTML="";
      let obj = JSON.parse(this.responseText);
      document.getElementById("temperature").innerHTML =
        Math.round((obj.list[0].main.temp-273.15)*100)/100 + " C";

      document.getElementById("humidity").innerHTML = obj.list[0].main.humidity+"%";
      document.getElementById("stav").innerHTML=obj.list[0].weather[0].main;

      //zmeny pozadia podla description
      if (obj.list[0].weather[0].main == "Thunderstorm"){
        document.body.background='thunderstorm.jpg';
      }
      if (obj.list[0].weather[0].main == "Drizzle"){
        document.body.background='drizzle.jpg';
      }
      if (obj.list[0].weather[0].main == "Rain"){
        document.body.background='rain.jpg';
        document.body.style.color = "black";
      }
      if (obj.list[0].weather[0].main == "Snow"){
        document.body.background='snow.jpg';
        document.getElementById('replaceDIV').style.color = "white";
        document.getElementById('stav').style.color = "white";
      }
      if (obj.list[0].weather[0].main == "Atmosphere"){
        document.body.background='atmosphere.jpg';
      }
      if (obj.list[0].weather[0].main == "Clear"){
        document.body.background='clear.jpg';
        document.body.style.color = "black";
      }
      if (obj.list[0].weather[0].main == "Clouds"){
        document.body.background='clouds.jpg';
        document.body.style.color = "black";
      }

      document.getElementById('replaceDIV').innerHTML= city1 + ", "+country1;
      document.getElementById("mraky").innerHTML=obj.list[0].clouds.all + "%";
      document.getElementById("vietor").innerHTML=obj.list[0].wind.speed + " meter/sec";
    }
    else {
      document.getElementById("wrong").innerHTML="Wrong input"; //tu zmenit, kde presne ma vypisat wrong input
    }
  };
  xhttp.open("GET", req4, true);
  xhttp.send();

}
