
function getWeather() {
  let city1 = document.getElementById('city').value;
  let city=city1;
  city = encodeURI(city);
  let country1 = document.getElementById('country').value;
  let country=country1;
  country= encodeURI(country);
  console.log('city: '+city+ ' country: '+country);
  let appid="850bf2f1030e0ac754a4acb6582d1d97";
  let req="http://api.openweathermap.org/data/2.5/forecast?";
  req=req+"q="+city;
  req=req+","+country;
  req=req+"&mode=HTML";
  req=req+"&appid="+appid;
  console.log(req);
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
  xhttp.open("GET", req, true);
  xhttp.send();

}
