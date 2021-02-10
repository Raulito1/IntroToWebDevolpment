/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $('.editPower-btn').click(function(event){
        $('#editSuperpowerDiv').show();
        $('#addSuperpowerDiv').hide();
    });
    
    
    $('#cancelEditPower-btn').click(function (event) {
        $('#editSuperpowerDiv').hide();
        $('#addSuperpowerDiv').show();
    });

    $('.editPerson-btn').click(function (event) {
        $('#editSuperheroDiv').show();
        $('#addSuperheroDiv').hide();
    });

    $('#cancelEditPerson-btn').click(function (event) {
        $('#editSuperheroDiv').hide();
        $('#addSuperheroDiv').show();
    });

    $('.editLocation-btn').click(function (event) {
        $('#editLocationDiv').show();
        $('#addLocationDiv').hide();
    });

    $('#cancelEditLocation-btn').click(function (event) {
        $('#editLocationDiv').hide();
        $('#addLocationDiv').show();
    });

    $('.editOrganization-btn').click(function (event) {
        $('#editOrganizationDiv').show();
        $('#addOrganizationDiv').hide();
    });

    $('#cancelEditOrganization-btn').click(function (event) {
        $('#editOrganizationDiv').hide();
        $('#addOrganizationDiv').show();
    });

    $('.editSighting-btn').click(function (event) {
        $('#editSightingDiv').show();
        $('#addSightingDiv').hide();
    });

    $('#cancelEditSighting-btn').click(function (event) {
        $('#editSightingDiv').hide();
        $('#addSightingDiv').show();
    });
});

function editSuperpower(superpowerId) {
    clearEditSuperpowerDiv();
    
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperHeroSightings/editPower?superpowerId=' + superpowerId,
        success: function (superpowerToEdit) {
            var name = superpowerToEdit.name;
            var description = superpowerToEdit.description;
            $('#superpowerId').val(superpowerId);
            $('#name').val(name);
            $('#description').val(description);
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}

function clearEditSuperpowerDiv() {
    $('#superpowerId').empty();
    $('#name').empty();
    $('#description').empty();
}

function editSuperhero(personId) {
    clearEditSuperheroDiv();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperHeroSightings/editPerson?personId=' + personId,
        success: function (personToEdit) {
            var name = personToEdit.name;
            var description = personToEdit.description;
            var superpowers = personToEdit.superpowers;
            $('#personId').val(personId);
            $('#name').val(name);
            $('#description').val(description);
            var currentPowers = '';
            $.each(superpowers, function (index, power) {
                var powerName = power.name;
                currentPowers += powerName + ' | ';
            });
            $('#currentPowers').val(currentPowers);
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}

function clearEditSuperheroDiv() {
    $('#personId').empty();
    $('#name').empty();
    $('#description').empty();
}

function editLocation(locationId) {
    clearEditLocationDiv();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperHeroSightings/editLocation?locationId=' + locationId,
        success: function (locationToEdit) {
            var name = locationToEdit.name;
            var description = locationToEdit.description;
            var street = locationToEdit.street;
            var city = locationToEdit.city;
            var state = locationToEdit.state;
            var country = locationToEdit.country;
            var longitude = locationToEdit.longitude;
            var latitude = locationToEdit.latitude;
            $('#locationId').val(locationId);
            $('#name').val(name);
            $('#description').val(description);
            $('#street').val(street);
            $('#city').val(city);
            $('#state').val(state);
            $('#country').val(country);
            $('#longitude').val(longitude);
            $('#latitude').val(latitude);
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}

function clearEditLocationDiv() {
    $('#locationId').empty();
    $('#name').empty();
    $('#description').empty();
    $('#street').empty();
    $('#city').empty();
    $('#state').empty();
    $('#country').empty();
    $('#longitude').empty();
    $('#latitude').empty();
}

function editOrganization(organizationId) {
    clearEditOrganizationDiv();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperHeroSightings/editOrganization?organizationId=' + organizationId,
        success: function (organizationToEdit) {
            var name = organizationToEdit.name;
            var description = organizationToEdit.description;
            var phone = organizationToEdit.phone;
            var email = organizationToEdit.email;
            var members = organizationToEdit.members;
            var currentLocation = organizationToEdit.location.name;
            var currentMembers = '';
            $.each(members, function (index, member) {
                var memberName = member.name;
                currentMembers += memberName + ' | ';
            });
            $('#organizationId').val(organizationId);
            $('#name').val(name);
            $('#description').val(description);
            $('#phone').val(phone);
            $('#email').val(email);
            $('#currentLocation').val(currentLocation);
            $('#currentMembers').val(currentMembers);

        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}

function clearEditOrganizationDiv() {
    $('#organizationId').empty();
    $('#name').empty();
    $('#description').empty();
    $('#phone').empty();
    $('#email').empty();
}

function editSighting(sightingId) {
    clearEditSightingDiv();

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperHeroSightings/editSighting?sightingId=' + sightingId,
        success: function (sightingToEdit) {
            var currentLocation = sightingToEdit.location.name;
            var date = sightingToEdit.date;
            
            
            var persons = sightingToEdit.persons;
            var currentPersons = '';
            $.each(persons, function (index, person) {
                var personName = person.name;
                currentPersons += personName + ' | ';
            });
            $('#currentLocation').val(currentLocation);
            $('#date').val(date.toString());
            $('#currentPersons').val(currentPersons);
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });

}

function clearEditSightingDiv() {
    $('#currentLocation').empty();
    $('#date').empty();
    $('#currentPersons').empty();
    
    }  