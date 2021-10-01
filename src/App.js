import React from 'react';
//import { db } from 'firebase';
import foodinsert from "./components/Foodinsert";
import "./app.css";
//import { auth } from './services/firebase'
import {db} from './firebasenode';
import { collection, getDocs } from "firebase/firestore";



class App extends React.Component{
   
  state = {
    foods :null 
  }
  async componentDidMount()
  {
    console.log('mounted')

    const querySnapshot = await getDocs(collection(db, 'foods'));
querySnapshot.forEach((doc) => {
  console.log(`${doc.id} => ${doc.data()}`);
});
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
  addNewFood = () => {
    db.collection('foods')
    .add({
      //insert user info
      //name: "fname", country: "cname", ingredient: "iname", cookingtime:  " ciname" 
    })
  }


  render(){
    return(
     
      <div className= 'app'>
     
         <foodinsert/>

        <h1>Upperline Foods</h1>
        <div>
        <p>Name: <input /></p>
        <p>Country: <input /></p>
        <p>Ingredients: <input /></p>
        <p>Cooking Time: <input /></p>
        </div>
        <button onClick={this.addNewFood}> Add New Food</button>
        {
          this.state.foods && 
          this.state.foods.map( foods => {
            return (
              <div>
                <p>{foods.name} and {foods.country}</p>
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