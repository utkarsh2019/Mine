import React from "react";
import "../css/bootstrap.css"
import "../css/popup.css";
import { compileFunction } from "vm";

class Popup extends React.Component {
    render() {

        if (this.props.confirm === 'true') {
            return (
                <div class="popup">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">{this.props.header}</h4>
                                <button type="button" class="close" onClick={this.props.closePopup}>&times;</button>
                            </div>
                            <div class="modal-body">
                                <p>{this.props.text}</p>
                            </div>
                            <div class="modal-footer" id="type">
                                <button type="button" class="btn btn-danger" onClick={this.props.action}>Yes</button>
                                <button type="button" class="btn btn-info" onClick={this.props.closePopup}>Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }

        return (
            <div class="popup">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" onClick={this.props.closePopup}>&times;</button>
                            <h4 class="modal-title">{this.props.header}</h4>
                        </div>
                        <div class="modal-body">
                            <p>{this.props.text}</p>
                        </div>
                        <div class="modal-footer" id="type">
                            <button type="button" class="btn btn-info" onClick={this.props.closePopup}>Close</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default Popup;