<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template name="word_to_iri">
	<xsl:param name="text" />
	<xsl:value-of select="concat(upper-case(substring($text,1,1)), substring($text, 2))"/>
</xsl:template>

<xsl:template name="word_to_iri_lowercase">
	<xsl:param name="text" />
        <xsl:choose>
            <xsl:when test="position()=1">
                <xsl:value-of select="concat(lower-case(substring($text,1,1)), substring($text, 2))"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="concat(upper-case(substring($text,1,1)), substring($text, 2))"/>
            </xsl:otherwise>
        </xsl:choose>
</xsl:template>

<xsl:template name="verb_to_object_property">
	<xsl:param name="verb" />
	ObjectProperty(
	<xsl:value-of select="$verb/@lemma"/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$verb"/>)
</xsl:template>

<xsl:template name="verb_and_xcomp_to_object_property">
	<xsl:param name="verb" />
        <xsl:param name="xcomp" />
	ObjectProperty(
	<xsl:value-of select="$verb/@lemma"/>To<xsl:value-of select="$xcomp/@lemma"/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$verb"/> to <xsl:value-of select="$xcomp"/>)
</xsl:template>

<xsl:template name="noun_to_object_property_of">
	<xsl:param name="noun" />
	ObjectProperty(
	<xsl:value-of select="$noun/@lemma"/>Of
	<xsl:text> </xsl:text>
	<xsl:value-of select="$noun/@lemma"/> of)
</xsl:template>

<xsl:template name="adj_to_data_property">
	<xsl:param name="adj" />
	DataProperty(
	is<xsl:call-template name="word_to_iri">
            <xsl:with-param name="text" select="$adj/@lemma" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
        XSD_BOOLEAN
        <xsl:text> </xsl:text>
	is <xsl:value-of select="$adj/@lemma"/>)
</xsl:template>

<xsl:template name="verb_to_object_property_no_annotation">
	<xsl:param name="verb" />
	ObjectProperty(
	<xsl:value-of select="$verb/@lemma"/>)
</xsl:template>

<xsl:template name="noun_to_class">
	<xsl:param name="noun" />
	Class(
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="$noun/@lemma" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="noun_phrase_to_class">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Class(
   		<xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*[position()>1]">
      		<xsl:value-of select="."/>
	   		<xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Class(
   		<xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*">
      		<xsl:value-of select="."/>
	   		<xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_of_object_property">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      ObjectPropertyWithInverse(
   		<xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri_lowercase">
                    <xsl:with-param name="text" select="." />
                </xsl:call-template>
   		</xsl:for-each>Of
   		<xsl:text> </xsl:text>
                have<xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
                </xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*[position()>1]">
      		<xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each> of)
      </xsl:when>
      <xsl:otherwise>
      ObjectPropertyWithInverse(
   		<xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri_lowercase">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>Of
   		<xsl:text> </xsl:text>
                have<xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*">
      		<xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each> of)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_have_object_property">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      ObjectPropertyWithInverse(
   		have<xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
                </xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
                <xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri_lowercase">
                    <xsl:with-param name="text" select="." />
                </xsl:call-template>
   		</xsl:for-each>Of
   		<xsl:text> </xsl:text>
   		have <xsl:for-each select="$NP/*[position()>1]">
      		<xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      ObjectPropertyWithInverse(
   		have<xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
                <xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri_lowercase">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>Of
   		<xsl:text> </xsl:text>
   		have <xsl:for-each select="$NP/*">
      		<xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>


<xsl:template name="noun_to_individual">
	<xsl:param name="noun" />
	Individual(		
	<xsl:value-of select="$noun"/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="pronoun_to_individual">
	<xsl:param name="prp" />
	Individual(		
	<xsl:value-of select="translate(//COREF[@id=$prp/@id],' ','')"/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="//COREF[@id=$prp/@id]"/>)
</xsl:template>


<xsl:template name="noun_to_individual_no_annotation">
	<xsl:param name="noun" />
	Individual(		
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="noun_phrase_to_individual">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Individual(
   		<xsl:for-each select="$NP/*[position()>1]">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*[position()>1]">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Individual(
   		<xsl:for-each select="$NP/*">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_to_individual_before_cc">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Individual(
   		<xsl:for-each select="$NP/CC/preceding-sibling::*[position()>1]">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/CC/preceding-sibling::*[position()>1]">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Individual(
   		<xsl:for-each select="$NP/CC/preceding-sibling::*">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/CC/preceding-sibling::*">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_to_individual_after_cc">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Individual(
   		<xsl:for-each select="$NP/CC/following-sibling::*[position()>1]">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/CC/following-sibling::*[position()>1]">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Individual(
   		<xsl:for-each select="$NP/CC/following-sibling::*">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/CC/following-sibling::*">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_to_individual_no_annotation">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Individual(
   		<xsl:for-each select="$NP/*[position()>1]">
                    <xsl:value-of select="."/>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Individual(
   		<xsl:for-each select="$NP/*">
                    <xsl:value-of select="."/>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

</xsl:stylesheet>