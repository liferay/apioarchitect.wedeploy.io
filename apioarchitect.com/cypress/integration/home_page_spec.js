describe('The Home Page', () => {
    it('successfully loads', () => cy.visit('/'));

    it('contains valid navigation bar', () => {
        cy.get('.navbar-nav > li')
            .as('navbar')
            .its('length')
            .should('be.eq', 5);

        cy.get('@navbar').eq(0).should('contain', 'Docs');
        cy.get('@navbar').eq(1).should('contain', 'Blog');
        cy.get('@navbar').eq(2).should('contain', 'Onboarding');
        cy.get('@navbar').eq(3).should('contain', 'Updates');
        cy.get('@navbar').eq(4).find('a')
            .should('have.attr', 'href')
            .should('be.eq', 'https://github.com/liferay/com-liferay-apio-architect');
    });

    it('contains valid container links', () => {
        cy.get('div.intro > div > a')
            .as('container-links')
            .its('length')
            .should('be.eq', 2);

        cy.get('.navbar-nav > li:nth-child(1) > a').then(link => {
            cy.get('@container-links').eq(0)
                .should('have.attr', 'href')
                .should('be.eq', link.attr('href'));
        });

        cy.get('.navbar-nav > li:nth-child(3) > a').then(link => {
            cy.get('@container-links').eq(1)
                .should('have.attr', 'href')
                .should('be.eq', link.attr('href'));
        });
    });

    it('contains valid contact-us links', () => {
        cy.get('.navbar-nav > li:nth-child(5) > a').then(link => {
            cy.get('section.contact-us a')
                .should('have.attr', 'href')
                .should('be.eq', link.attr('href'));
        });
    });
});