// Validator for String password
/*
const strongPassword = function () {
  return {
    validate: function (input) {
      const value = input.value;
      if (value === "") {
        return {
          valid: true,
        };
      }

      // Check the password strength
      if (value.length < 8) {
        return {
          valid: false,
        };
      }

      // The password does not contain any uppercase character
      if (value === value.toLowerCase()) {
        return {
          valid: false,
        };
      }

      // The password does not contain any uppercase character
      if (value === value.toUpperCase()) {
        return {
          valid: false,
        };
      }

      // The password does not contain any digit
      if (value.search(/[0-9]/) < 0) {
        return {
          valid: false,
        };
      }

      return {
        valid: true,
      };
    },
  };
};

// Register the validator
FormValidation.validators.checkPassword = strongPassword;

// Use new validator
FormValidation.formValidation(document.getElementById("registerform"), {
  fields: {
    password: {
      validators: {
        // checkPassword is name of new validator
        checkPassword: {
          message: "The password is too weak",/*
        },
      },
    },
  },
});
*/