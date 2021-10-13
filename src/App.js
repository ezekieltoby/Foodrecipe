import React from 'react';
//import { db } from 'firebase';
import foodinsert from "./components/Foodinsert";
import "./app.css";
//import { auth } from './services/firebase'
import {db} from './firebasenode';
import { collection, getDocs, addDoc, onSnapshot, setDoc, doc, refEqual } from "firebase/firestore";
import { useEffect, useState } from "react";
//import  firebase  from './firebasenode';



class App extends React.Component{
 


  state = {
    foods :null,
    countries:['Algeria','Mexico'] 
  }
  //async connects to firebase
  async componentDidMount()
  {
    console.log('mounted')

    const querySnapshot = await getDocs(collection(db, 'foods'));
querySnapshot.forEach((doc) => {
  console.log(`${doc.id} => ${doc.data().country}`);
  this.state.countries.push(doc.data().country);
});
this.setState({
  countries: this.state.countries
})
  /*  collection(db, 'foods')
    .get()
    .then (snapshot => {
      const foods = []
      snapshot.forEach(doc => {
        const data = doc.data()
        foods.push(data)
      })
      this.setState({foods: foods})
      //console.log(snapshot)

    })
    .catch( error => console.log(error) )
  */}

  //addss
  async addNewFood() {
    

    try {
      const docRef = await addDoc(collection(db, 'foods'), {
        name: "db.name",
        country: "lkjk",
        ingredients: 'lkj',
        cookingtime: ''
      });
      console.log("Document written with ID: ", docRef.id);
    } catch (e) {
      console.error("Error adding document: ", e);
    }
    

   /* db.collection('foods')
    .add({
      //insert user info
      //name: "fname", country: "cname", ingredient: "iname", cookingtime:  " ciname" 
    })*/
  }




   render(){ 
    const handleNew = async () => {
      const name = prompt("Enter name");
      const country = prompt("Enter country");
      const ingredients = prompt("Enter ingredients");
      const cookingtime = prompt("Enter cookingtime");


      const collectionRef = doc(collection(db, "foods"));
      const payload = {name, country, ingredients, cookingtime}
      await setDoc(collectionRef, payload);
    }
   /*  const [foods, setFoods] = useState([{ name: "Loading...", id: "initial" }]);
    
   useEffect(
      () =>
        onSnapshot(collection(db, "foods"), (snapshot) =>
          setFoods(snapshot.docs.map((doc) => ({ ...doc.data(), id: doc.id })))
        ),
      []
    );
    */
   const unsub = onSnapshot(doc(db, "foods", "SF"), (doc) => {
    console.log("Current data: ", doc.data());
});



/*const ref = firebase.firestore().collection("foods")
const[data, setdata] = useState([])
const [loader, setloader] = useState(true)

function getData2(){
  ref.get().then((item) => {
    const items = item.docs.map((doc) => doc.data())
    setdata(items)
    setloader(false)

  })
}

useEffect(() =>{
getData2()
}, [])
*/




const querySnapshot = getDocs(collection(db, 'foods')).then((data)=>{
  console.log(data);
  data.forEach((doc) => {
    console.log(`hkhk ${doc.id} => ${doc.data().country}`);
  });
});
/*querySnapshot.forEach((doc) => {
  console.log(`${doc.id} => ${doc.data().country}`);
});*/
    return(
     
      
<div className= 'app'>
<div className= 'root'>
<button className="button"> Display Foods</button>


</div>
  

         <foodinsert/>

        <h1>Upperline Foods</h1>
        <div>
        <p>Name: <input /></p>
        <p>Country: <input /></p>
        <p>Ingredients: <input /></p>
        <p>Cooking Time: <input /></p>
        </div>


        <button onClick={handleNew}> Add New Food</button>
        {
          this.state.countries && 
          this.state.countries.map( foods => {
            
            return (
              <div>
                <p>{foods} </p>
                 </div>
            )
          })
        }
      </div>
    )
  }
  
}

export default App

/*
import React from 'react';
import 'firebase/auth';
import 'firebase/firestore';
import foodinsert from "./components/foodinsert";
 function App() {
   return(
     <div className="App">
       <foodinsert />
     </div>
   );
 }
 export default App;
 */