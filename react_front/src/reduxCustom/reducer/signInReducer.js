import actionTypes from "reduxCustom/config/actionTypes"

// action types
const SIGN_IN = actionTypes.SIGN_IN;
const SIGN_OUT = actionTypes.SIGN_OUT;

// action creator
export const signIn = (payload) => {
  return {
    type: SIGN_IN,
    payload: payload,
  }
}
export const signOut = (payload) => {
  return {
    type: SIGN_OUT,
    payload: payload,
  }
}

// 초기 상태값
const initialState = {
  isSignIn: false,
}

// 리듀서
const signInReducer = (state = initialState, action) => {
  switch (action.type) {
    case SIGN_IN:
      return {
        isSignIn: state.isSignIn = true
      }

    case SIGN_OUT:
      return {
        isSignIn: state.isSignIn = false
      }

    default:
      return state;
  }
}

export default signInReducer;