// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {
  "count preloaded match results": browser => {
    browser
      .init()
      .url(browser.launchUrl + "#/match_results")
      .waitForElementVisible("#app")
      .assert.elementCount("tr", 13)
      .end();
  },

  "count league table rows": browser => {
    browser
      .init()
      .url(browser.launchUrl + "#/league_table")
      .waitForElementVisible("#app")
      .assert.elementCount("tr", 5)
      .end();
  }

  //TODO - Add tests that interact with the pages
};
