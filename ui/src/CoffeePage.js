import React from "react";
import AddCoffeeComponent from "./AddCoffeeComponent";

export default class CoffeePage extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="mx-3">
                Coffee page!
                <div className="w-50 mx-auto">
                    <AddCoffeeComponent/>
                </div>
            </div>
        );
    }

}